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
    public class DEALsController : ApiController
    {
        private Entities db = new Entities();

        // GET: api/DEALs
        public IQueryable<DEAL> GetDEALs()
        {
            return db.DEALs;
        }

        // GET: api/DEALs/5
        [ResponseType(typeof(DEAL))]
        public IHttpActionResult GetDEAL(int id)
        {
            DEAL dEAL = db.DEALs.Find(id);
            if (dEAL == null)
            {
                return NotFound();
            }

            return Ok(dEAL);
        }

        // PUT: api/DEALs/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutDEAL(int id, DEAL dEAL)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != dEAL.DEAL_ID)
            {
                return BadRequest();
            }

            db.Entry(dEAL).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!DEALExists(id))
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

        // POST: api/DEALs
        [ResponseType(typeof(DEAL))]
        public IHttpActionResult PostDEAL(DEAL dEAL)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.DEALs.Add(dEAL);
            db.SaveChanges();

            return CreatedAtRoute("DefaultApi", new { id = dEAL.DEAL_ID }, dEAL);
        }

        // DELETE: api/DEALs/5
        [ResponseType(typeof(DEAL))]
        public IHttpActionResult DeleteDEAL(int id)
        {
            DEAL dEAL = db.DEALs.Find(id);
            if (dEAL == null)
            {
                return NotFound();
            }

            db.DEALs.Remove(dEAL);
            db.SaveChanges();

            return Ok(dEAL);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool DEALExists(int id)
        {
            return db.DEALs.Count(e => e.DEAL_ID == id) > 0;
        }
    }
}