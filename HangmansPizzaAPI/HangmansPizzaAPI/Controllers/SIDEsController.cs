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
    public class SIDEsController : ApiController
    {
        private Entities db = new Entities();

        // GET: api/SIDEs
        public IQueryable<SIDE> GetSIDEs()
        {
            return db.SIDEs;
        }

        // GET: api/SIDEs/5
        [ResponseType(typeof(SIDE))]
        public IHttpActionResult GetSIDE(int id)
        {
            SIDE sIDE = db.SIDEs.Find(id);
            if (sIDE == null)
            {
                return NotFound();
            }

            return Ok(sIDE);
        }

        // PUT: api/SIDEs/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutSIDE(int id, SIDE sIDE)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != sIDE.SIDE_ID)
            {
                return BadRequest();
            }

            db.Entry(sIDE).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!SIDEExists(id))
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

        // POST: api/SIDEs
        [ResponseType(typeof(SIDE))]
        public IHttpActionResult PostSIDE(SIDE sIDE)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.SIDEs.Add(sIDE);
            db.SaveChanges();

            return CreatedAtRoute("DefaultApi", new { id = sIDE.SIDE_ID }, sIDE);
        }

        // DELETE: api/SIDEs/5
        [ResponseType(typeof(SIDE))]
        public IHttpActionResult DeleteSIDE(int id)
        {
            SIDE sIDE = db.SIDEs.Find(id);
            if (sIDE == null)
            {
                return NotFound();
            }

            db.SIDEs.Remove(sIDE);
            db.SaveChanges();

            return Ok(sIDE);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool SIDEExists(int id)
        {
            return db.SIDEs.Count(e => e.SIDE_ID == id) > 0;
        }
    }
}