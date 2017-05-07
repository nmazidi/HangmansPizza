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
    public class RIDER_ACTIVITYController : ApiController
    {
        private Entities db = new Entities();

        // GET: api/RIDER_ACTIVITY
        public IQueryable<RIDER_ACTIVITY> GetRIDER_ACTIVITY()
        {
            return db.RIDER_ACTIVITY;
        }

        // GET: api/RIDER_ACTIVITY/5
        [ResponseType(typeof(RIDER_ACTIVITY))]
        public IHttpActionResult GetRIDER_ACTIVITY(int id)
        {
            RIDER_ACTIVITY rIDER_ACTIVITY = db.RIDER_ACTIVITY.Find(id);
            if (rIDER_ACTIVITY == null)
            {
                return NotFound();
            }

            return Ok(rIDER_ACTIVITY);
        }

        // PUT: api/RIDER_ACTIVITY/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutRIDER_ACTIVITY(int id, RIDER_ACTIVITY rIDER_ACTIVITY)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != rIDER_ACTIVITY.SHIFT_ID)
            {
                return BadRequest();
            }

            db.Entry(rIDER_ACTIVITY).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!RIDER_ACTIVITYExists(id))
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

        // POST: api/RIDER_ACTIVITY
        [ResponseType(typeof(RIDER_ACTIVITY))]
        public IHttpActionResult PostRIDER_ACTIVITY(RIDER_ACTIVITY rIDER_ACTIVITY)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.RIDER_ACTIVITY.Add(rIDER_ACTIVITY);
            db.SaveChanges();

            return CreatedAtRoute("DefaultApi", new { id = rIDER_ACTIVITY.SHIFT_ID }, rIDER_ACTIVITY);
        }

        // DELETE: api/RIDER_ACTIVITY/5
        [ResponseType(typeof(RIDER_ACTIVITY))]
        public IHttpActionResult DeleteRIDER_ACTIVITY(int id)
        {
            RIDER_ACTIVITY rIDER_ACTIVITY = db.RIDER_ACTIVITY.Find(id);
            if (rIDER_ACTIVITY == null)
            {
                return NotFound();
            }

            db.RIDER_ACTIVITY.Remove(rIDER_ACTIVITY);
            db.SaveChanges();

            return Ok(rIDER_ACTIVITY);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool RIDER_ACTIVITYExists(int id)
        {
            return db.RIDER_ACTIVITY.Count(e => e.SHIFT_ID == id) > 0;
        }
    }
}