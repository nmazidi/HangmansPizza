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
    public class CUSTOMERsController : ApiController
    {
        public static String HashFunction(String unhashedPassword)
        {
            var hashedPassword = "";
            var salt = "";
            var verify = false;

            salt = Crypto.GenerateSalt();
            unhashedPassword += salt;
            hashedPassword = Crypto.HashPassword(unhashedPassword);

            verify = Crypto.VerifyHashedPassword(hashedPassword, unhashedPassword);
            if (verify)
            {
                return hashedPassword;
            }
            else
            {
                return null;
            }

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
            cUSTOMER.CUSTOMER_PASSWORD = HashFunction(cUSTOMER.CUSTOMER_PASSWORD);
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
            cUSTOMER.CUSTOMER_PASSWORD = HashFunction(cUSTOMER.CUSTOMER_PASSWORD);
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