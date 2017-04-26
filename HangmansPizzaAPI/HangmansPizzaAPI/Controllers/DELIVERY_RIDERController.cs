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
using HangmansPizzaAPI.Models;

namespace HangmansPizzaAPI.Controllers
{
    public class DELIVERY_RIDERController : ApiController
    {
        private Entities5 db = new Entities5();

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
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != dELIVERY_RIDER.RIDER_ID)
            {
                return BadRequest();
            }

            db.Entry(dELIVERY_RIDER).State = EntityState.Modified;

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
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.DELIVERY_RIDER.Add(dELIVERY_RIDER);
            db.SaveChanges();

            return CreatedAtRoute("DefaultApi", new { id = dELIVERY_RIDER.RIDER_ID }, dELIVERY_RIDER);
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