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
    public class ADDRESSesController : ApiController
    {
        private Entities db = new Entities();

        // GET: api/ADDRESSes
        public IQueryable<ADDRESS> GetADDRESSes()
        {
            return db.ADDRESSes;
        }

        // GET: api/ADDRESSes/5
        [ResponseType(typeof(ADDRESS))]
        public IHttpActionResult GetADDRESS(short id)
        {
            ADDRESS aDDRESS = db.ADDRESSes.Find(id);
            if (aDDRESS == null)
            {
                return NotFound();
            }

            return Ok(aDDRESS);
        }

        // PUT: api/ADDRESSes/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutADDRESS(short id, ADDRESS aDDRESS)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != aDDRESS.ADDRESS_ID)
            {
                return BadRequest();
            }

            db.Entry(aDDRESS).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!ADDRESSExists(id))
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

        // POST: api/ADDRESSes
        [ResponseType(typeof(ADDRESS))]
        public IHttpActionResult PostADDRESS(ADDRESS aDDRESS)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.ADDRESSes.Add(aDDRESS);
            db.SaveChanges();

            return CreatedAtRoute("DefaultApi", new { id = aDDRESS.ADDRESS_ID }, aDDRESS);
        }

        // DELETE: api/ADDRESSes/5
        [ResponseType(typeof(ADDRESS))]
        public IHttpActionResult DeleteADDRESS(short id)
        {
            ADDRESS aDDRESS = db.ADDRESSes.Find(id);
            if (aDDRESS == null)
            {
                return NotFound();
            }

            db.ADDRESSes.Remove(aDDRESS);
            db.SaveChanges();

            return Ok(aDDRESS);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool ADDRESSExists(short id)
        {
            return db.ADDRESSes.Count(e => e.ADDRESS_ID == id) > 0;
        }
    }
}