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
    public class DELIVERiesController : ApiController
    {
        private Entities db = new Entities();

        // GET: api/DELIVERies
        public IQueryable<DELIVERY> GetDELIVERies()
        {
            return db.DELIVERies;
        }

        // GET: api/DELIVERies/5
        [ResponseType(typeof(DELIVERY))]
        public IHttpActionResult GetDELIVERY(int id)
        {
            DELIVERY dELIVERY = db.DELIVERies.Find(id);
            if (dELIVERY == null)
            {
                return NotFound();
            }

            return Ok(dELIVERY);
        }

        // PUT: api/DELIVERies/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutDELIVERY(int id, DELIVERY dELIVERY)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != dELIVERY.DELIVERY_ID)
            {
                return BadRequest();
            }

            db.Entry(dELIVERY).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!DELIVERYExists(id))
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

        // POST: api/DELIVERies
        [ResponseType(typeof(DELIVERY))]
        public IHttpActionResult PostDELIVERY(DELIVERY dELIVERY)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.DELIVERies.Add(dELIVERY);
            db.SaveChanges();

            return CreatedAtRoute("DefaultApi", new { id = dELIVERY.DELIVERY_ID }, dELIVERY);
        }

        // DELETE: api/DELIVERies/5
        [ResponseType(typeof(DELIVERY))]
        public IHttpActionResult DeleteDELIVERY(int id)
        {
            DELIVERY dELIVERY = db.DELIVERies.Find(id);
            if (dELIVERY == null)
            {
                return NotFound();
            }

            db.DELIVERies.Remove(dELIVERY);
            db.SaveChanges();

            return Ok(dELIVERY);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool DELIVERYExists(int id)
        {
            return db.DELIVERies.Count(e => e.DELIVERY_ID == id) > 0;
        }
    }
}