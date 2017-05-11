using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Description;
using HangmansPizzaAPI;
using System.Web.Helpers;

namespace HangmansPizzaAPI.Controllers
{
    public class ADMINsController : ApiController
    {
        public static string[] HashFunction(String unhashedPassword, String saltFromDB)
        {
            var hashedPassword = "";
            var saltedPassword = "";
            var salt = "";

            if (saltFromDB != "test")
            {
                salt = saltFromDB;
            }
            else
            {
                salt = Crypto.GenerateSalt();
            }

            saltedPassword = unhashedPassword + salt;
            hashedPassword = Crypto.SHA256(saltedPassword);
            string[] arrayToReturn = { hashedPassword, salt };

            return arrayToReturn;
        }
        private Entities db = new Entities();

        // GET: api/ADMINs
        public IQueryable<ADMIN> GetADMINs()
        {
            return db.ADMINs;
        }
        
        // GET: api/ADMINs/5
        [ResponseType(typeof(ADMIN))]
        public IHttpActionResult GetADMIN(int id)
        {
            ADMIN aDMIN = db.ADMINs.Find(id);
            if (aDMIN == null)
            {
                return NotFound();
            }

            return Ok(aDMIN);
        }

        // PUT: api/ADMINs/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutADMIN(int id, ADMIN aDMIN)
        {
            if (aDMIN.PASSWORD != "HASHED")
            {
                var hashed = HashFunction(aDMIN.PASSWORD, aDMIN.PASSWORD_SALT);
                aDMIN.PASSWORD = hashed[0];
                aDMIN.PASSWORD_SALT = hashed[1];
            } else
            {
                var temp = db.ADMINs.Find(id);
                aDMIN.PASSWORD = temp.PASSWORD;
                aDMIN.PASSWORD_SALT = temp.PASSWORD_SALT;
            }
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != aDMIN.ADMIN_ID)
            {
                return BadRequest();
            }
            if (aDMIN.PASSWORD != null)
            {
                db.Entry(aDMIN).State = EntityState.Modified;
            } else
            {
                return InternalServerError();
            }

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!ADMINExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return StatusCode(HttpStatusCode.NoContent);
        }

        // POST: api/ADMINs
        [ResponseType(typeof(ADMIN))]
        public IHttpActionResult PostADMIN(ADMIN aDMIN)
        {
            var hashed = HashFunction(aDMIN.PASSWORD, aDMIN.PASSWORD_SALT);
            aDMIN.PASSWORD = hashed[0];
            aDMIN.PASSWORD_SALT = hashed[1];
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }
            if (aDMIN.PASSWORD != null)
            {
                db.ADMINs.Add(aDMIN);
                db.SaveChanges();
            }
            else
            {
                return InternalServerError();
            }

            return CreatedAtRoute("DefaultApi", new { id = aDMIN.ADMIN_ID }, aDMIN);
        }
        // POST: api/CUSTOMERs
        [ResponseType(typeof(ADMIN))]
        public IHttpActionResult LoginADMIN(string login, LoginDetails loginDetails)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }
            if (login.Equals("login"))
            {
                try
                {
                    ADMIN admin = db.ADMINs.Single(p => p.USERNAME == loginDetails.email);
                    int adminID = admin.ADMIN_ID;
                    var aDMIN = db.ADMINs.Find(adminID);

                    try
                    {
                        var hashedPassword = HashFunction(loginDetails.password, aDMIN.PASSWORD_SALT)[0];
                        if (hashedPassword == aDMIN.PASSWORD)
                        {
                            return Content(HttpStatusCode.Accepted, aDMIN);
                        }
                        else
                        {
                            return StatusCode(HttpStatusCode.Unauthorized);
                        }
                    }
                    catch (Exception e)
                    {
                        return StatusCode(HttpStatusCode.Forbidden);
                    }
                }
                catch (Exception e)
                {
                    return StatusCode(HttpStatusCode.Forbidden);
                }
            }
            return InternalServerError();
        }
        // DELETE: api/ADMINs/5
        [ResponseType(typeof(ADMIN))]
        public IHttpActionResult DeleteADMIN(int id)
        {
            ADMIN aDMIN = db.ADMINs.Find(id);
            if (aDMIN == null)
            {
                return NotFound();
            }

            db.ADMINs.Remove(aDMIN);
            db.SaveChanges();

            return Ok(aDMIN);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool ADMINExists(int id)
        {
            return db.ADMINs.Count(e => e.ADMIN_ID == id) > 0;
        }
    }
}