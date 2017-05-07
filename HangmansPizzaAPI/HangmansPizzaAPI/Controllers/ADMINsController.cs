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

namespace HangmansPizzaAPI.Controllers
{
    public class ADMINsController : ApiController
    {
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
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != aDMIN.ADMIN_ID)
            {
                return BadRequest();
            }

            db.Entry(aDMIN).State = EntityState.Modified;

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
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.ADMINs.Add(aDMIN);
            db.SaveChanges();

            return CreatedAtRoute("DefaultApi", new { id = aDMIN.ADMIN_ID }, aDMIN);
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