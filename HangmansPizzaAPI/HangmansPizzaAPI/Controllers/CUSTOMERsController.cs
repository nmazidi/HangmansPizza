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
using System.Web.Http.Results;
using System.Web.Mvc;

namespace HangmansPizzaAPI.Controllers
{
    public class CUSTOMERsController : ApiController
    {
        public static string[] HashFunction(String unhashedPassword, String saltFromDB)
        {
            var hashedPassword = "";
            var saltedPassword = "";
            var salt  = "";
            
            if (saltFromDB != null)
            {
                salt = saltFromDB;
            } else {
                salt = Crypto.GenerateSalt();
            }
            
            saltedPassword = unhashedPassword + salt;
            hashedPassword = Crypto.SHA256(saltedPassword);
            string[] arrayToReturn = { hashedPassword, salt };

            return arrayToReturn;
        }
        public JsonResult jsonReturn(CUSTOMER cust)
        {
            var result = new JsonResult();
            result.Data = new
            {
                cust
            };
            result.JsonRequestBehavior = JsonRequestBehavior.AllowGet;
            return result;
        }
        private Entities db = new Entities();

        // GET: api/CUSTOMERs
        public IQueryable<CUSTOMER> GetCUSTOMERs()
        {
            return db.CUSTOMERs;
        }
        // GET: api/CUSTOMERs/5
        [ResponseType(typeof(CUSTOMER))]
        public IHttpActionResult GetCUSTOMER(int id)
        {
            CUSTOMER cUSTOMER = db.CUSTOMERs.Find(id);

            if (cUSTOMER == null)
            {
                return NotFound();
            }

            return Ok(cUSTOMER);
        }

        // PUT: api/CUSTOMERs/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutCUSTOMER(int id, CUSTOMER cUSTOMER)
        {
            if (cUSTOMER.CUSTOMER_PASSWORD != "HASHED")
            {
                var hashed = HashFunction(cUSTOMER.CUSTOMER_PASSWORD, cUSTOMER.PASSWORD_SALT);
                cUSTOMER.CUSTOMER_PASSWORD = hashed[0];
                cUSTOMER.PASSWORD_SALT = hashed[1];
            }
            else {
                var temp = db.CUSTOMERs.Find(id);
                cUSTOMER.CUSTOMER_PASSWORD = temp.CUSTOMER_PASSWORD;
            }
            
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != cUSTOMER.CUSTOMER_ID)
            {
                return BadRequest();
            }
            if (cUSTOMER.CUSTOMER_PASSWORD != null)
            {
                db.Entry(cUSTOMER).State = EntityState.Modified;
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
                if (!CUSTOMERExists(id))
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

        // POST: api/CUSTOMERs
        [ResponseType(typeof(CUSTOMER))]
        public IHttpActionResult PostCUSTOMER(CUSTOMER cUSTOMER)
        {
            var hashed = HashFunction(cUSTOMER.CUSTOMER_PASSWORD, cUSTOMER.PASSWORD_SALT);
            cUSTOMER.CUSTOMER_PASSWORD = hashed[0];
            cUSTOMER.PASSWORD_SALT = hashed[1];
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }
            if (cUSTOMER.CUSTOMER_PASSWORD != null)
            {
                db.CUSTOMERs.Add(cUSTOMER);
                db.SaveChanges();
            } else
            {
                return InternalServerError();
            }
 
            return CreatedAtRoute("DefaultApi", new { id = cUSTOMER.CUSTOMER_ID }, cUSTOMER);
        }
        // POST: api/CUSTOMERs
        [ResponseType(typeof(CUSTOMER))]
        public IHttpActionResult LoginCUSTOMER(string login, LoginDetails loginDetails)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }
            if (login.Equals("login"))
            {
                try
                {
                    CUSTOMER customer = db.CUSTOMERs.Single(p => p.CUSTOMER_EMAIL == loginDetails.email); 
                    int custID = customer.CUSTOMER_ID;
                    var cUSTOMER = db.CUSTOMERs.Find(custID);
                
                try
                {
                    var hashedPassword = HashFunction(loginDetails.password, cUSTOMER.PASSWORD_SALT)[0];
                        if (hashedPassword == cUSTOMER.CUSTOMER_PASSWORD)
                        {
                            return Content(HttpStatusCode.Accepted, cUSTOMER);
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
                    return StatusCode(HttpStatusCode.NotAcceptable);
                }
            }
            return InternalServerError();
        }
        // DELETE: api/CUSTOMERs/5
        [ResponseType(typeof(CUSTOMER))]
        public IHttpActionResult DeleteCUSTOMER(int id)
        {
            CUSTOMER cUSTOMER = db.CUSTOMERs.Find(id);
            if (cUSTOMER == null)
            {
                return NotFound();
            }

            db.CUSTOMERs.Remove(cUSTOMER);
            db.SaveChanges();

            return Ok(cUSTOMER);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool CUSTOMERExists(int id)
        {
            return db.CUSTOMERs.Count(e => e.CUSTOMER_ID == id) > 0;
        }
    }
}