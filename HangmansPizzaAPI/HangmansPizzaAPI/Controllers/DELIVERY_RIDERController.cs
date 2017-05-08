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
    public class DELIVERY_RIDERController : ApiController
    {

        public static string[] HashFunction(String unhashedPassword, String saltFromDB)
        {
            var hashedPassword = "";
            var saltedPassword = "";
            var salt = "";

            if (saltFromDB != null)
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
        // GET: api/DELIVERY_RIDER
        public IQueryable<DELIVERY_RIDER> GetDELIVERY_RIDER()
        {
            return db.DELIVERY_RIDER;
        }

        // GET: api/DELIVERY_RIDER/5
        [ResponseType(typeof(DELIVERY_RIDER))]
        public IHttpActionResult GetDELIVERY_RIDER(int id)
        {
            DELIVERY_RIDER dELIVERY_RIDER = db.DELIVERY_RIDER.Find(id);
            if (dELIVERY_RIDER == null)
            {
                return NotFound();
            }

            return Ok(dELIVERY_RIDER);
        }

        // PUT: api/DELIVERY_RIDER/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutDELIVERY_RIDER(int id, DELIVERY_RIDER dELIVERY_RIDER)
        {
            var hashed = HashFunction(dELIVERY_RIDER.RIDER_PASSWORD, dELIVERY_RIDER.PASSWORD_SALT);
            dELIVERY_RIDER.RIDER_PASSWORD = hashed[0];
            dELIVERY_RIDER.PASSWORD_SALT = hashed[1];
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != dELIVERY_RIDER.RIDER_ID)
            {
                return BadRequest();
            }
            if (dELIVERY_RIDER.RIDER_PASSWORD != null)
            {
                db.Entry(dELIVERY_RIDER).State = EntityState.Modified;
            }  else
            {
                return InternalServerError();
            }
            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!DELIVERY_RIDERExists(id))
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

        // POST: api/DELIVERY_RIDER
        [ResponseType(typeof(DELIVERY_RIDER))]
        public IHttpActionResult PostDELIVERY_RIDER(DELIVERY_RIDER dELIVERY_RIDER)
        {
            var hashed = HashFunction(dELIVERY_RIDER.RIDER_PASSWORD, dELIVERY_RIDER.PASSWORD_SALT);
            dELIVERY_RIDER.RIDER_PASSWORD = hashed[0];
            dELIVERY_RIDER.PASSWORD_SALT = hashed[1];
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }
            if (dELIVERY_RIDER.RIDER_PASSWORD != null)
            {
                db.DELIVERY_RIDER.Add(dELIVERY_RIDER);
                db.SaveChanges();
            } else
            {
                return InternalServerError();
            }
            
            return CreatedAtRoute("DefaultApi", new { id = dELIVERY_RIDER.RIDER_ID }, dELIVERY_RIDER);
        }
        // POST: api/DELIVERY_RIDER
        [ResponseType(typeof(void))]
        public IHttpActionResult LoginRIDER(string login, LoginDetails loginDetails)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }
            if (login.Equals("login"))
            {
                try
                {
                    DELIVERY_RIDER rider = db.DELIVERY_RIDER.Single(p => p.RIDER_EMAIL == loginDetails.email);
                    int riderID = rider.RIDER_ID;
                    var rIDER = db.DELIVERY_RIDER.Find(riderID);

                    try
                    {
                        var hashedPassword = HashFunction(loginDetails.password, rIDER.PASSWORD_SALT)[0];
                        if (hashedPassword == rIDER.RIDER_PASSWORD)
                        {
                            return StatusCode(HttpStatusCode.Accepted);
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
        // DELETE: api/DELIVERY_RIDER/5
        [ResponseType(typeof(DELIVERY_RIDER))]
        public IHttpActionResult DeleteDELIVERY_RIDER(int id)
        {
            DELIVERY_RIDER dELIVERY_RIDER = db.DELIVERY_RIDER.Find(id);
            if (dELIVERY_RIDER == null)
            {
                return NotFound();
            }

            db.DELIVERY_RIDER.Remove(dELIVERY_RIDER);
            db.SaveChanges();

            return Ok(dELIVERY_RIDER);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool DELIVERY_RIDERExists(int id)
        {
            return db.DELIVERY_RIDER.Count(e => e.RIDER_ID == id) > 0;
        }
    }
}