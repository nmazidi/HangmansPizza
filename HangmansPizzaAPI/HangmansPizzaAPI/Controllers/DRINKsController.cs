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
    public class DRINKsController : ApiController
    {
        private Entities5 db = new Entities5();

        // GET: api/DRINKs
        public IQueryable<DRINK> GetDRINKs()
        {
            return db.DRINKs;
        }

        // GET: api/DRINKs/5
        [ResponseType(typeof(DRINK))]
        public IHttpActionResult GetDRINK(int id)
        {
            DRINK dRINK = db.DRINKs.Find(id);
            if (dRINK == null)
            {
                return NotFound();
            }

            return Ok(dRINK);
        }

        // PUT: api/DRINKs/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutDRINK(int id, DRINK dRINK)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != dRINK.DRINK_ID)
            {
                return BadRequest();
            }

            db.Entry(dRINK).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!DRINKExists(id))
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

        // POST: api/DRINKs
        [ResponseType(typeof(DRINK))]
        public IHttpActionResult PostDRINK(DRINK dRINK)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.DRINKs.Add(dRINK);
            db.SaveChanges();

            return CreatedAtRoute("DefaultApi", new { id = dRINK.DRINK_ID }, dRINK);
        }

        // DELETE: api/DRINKs/5
        [ResponseType(typeof(DRINK))]
        public IHttpActionResult DeleteDRINK(int id)
        {
            DRINK dRINK = db.DRINKs.Find(id);
            if (dRINK == null)
            {
                return NotFound();
            }

            db.DRINKs.Remove(dRINK);
            db.SaveChanges();

            return Ok(dRINK);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool DRINKExists(int id)
        {
            return db.DRINKs.Count(e => e.DRINK_ID == id) > 0;
        }
    }
}