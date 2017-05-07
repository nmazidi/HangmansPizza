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
    public class TRANSACTIONsController : ApiController
    {
        private Entities db = new Entities();

        // GET: api/TRANSACTIONs
        public IQueryable<TRANSACTION> GetTRANSACTIONs()
        {
            return db.TRANSACTIONs;
        }

        // GET: api/TRANSACTIONs/5
        [ResponseType(typeof(TRANSACTION))]
        public IHttpActionResult GetTRANSACTION(int id)
        {
            TRANSACTION tRANSACTION = db.TRANSACTIONs.Find(id);
            if (tRANSACTION == null)
            {
                return NotFound();
            }

            return Ok(tRANSACTION);
        }

        // PUT: api/TRANSACTIONs/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutTRANSACTION(int id, TRANSACTION tRANSACTION)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != tRANSACTION.TRANSACTION_ID)
            {
                return BadRequest();
            }

            db.Entry(tRANSACTION).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!TRANSACTIONExists(id))
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

        // POST: api/TRANSACTIONs
        [ResponseType(typeof(TRANSACTION))]
        public IHttpActionResult PostTRANSACTION(TRANSACTION tRANSACTION)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.TRANSACTIONs.Add(tRANSACTION);
            db.SaveChanges();

            return CreatedAtRoute("DefaultApi", new { id = tRANSACTION.TRANSACTION_ID }, tRANSACTION);
        }

        // DELETE: api/TRANSACTIONs/5
        [ResponseType(typeof(TRANSACTION))]
        public IHttpActionResult DeleteTRANSACTION(int id)
        {
            TRANSACTION tRANSACTION = db.TRANSACTIONs.Find(id);
            if (tRANSACTION == null)
            {
                return NotFound();
            }

            db.TRANSACTIONs.Remove(tRANSACTION);
            db.SaveChanges();

            return Ok(tRANSACTION);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool TRANSACTIONExists(int id)
        {
            return db.TRANSACTIONs.Count(e => e.TRANSACTION_ID == id) > 0;
        }
    }
}