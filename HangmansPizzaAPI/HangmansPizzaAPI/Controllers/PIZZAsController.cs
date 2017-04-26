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
    public class PIZZAsController : ApiController
    {
        private Entities5 db = new Entities5();

        // GET: api/PIZZAs
        public IQueryable<PIZZA> GetPIZZAs()
        {
            return db.PIZZAs;
        }

        // GET: api/PIZZAs/5
        [ResponseType(typeof(PIZZA))]
        public IHttpActionResult GetPIZZA(int id)
        {
            PIZZA pIZZA = db.PIZZAs.Find(id);
            if (pIZZA == null)
            {
                return NotFound();
            }

            return Ok(pIZZA);
        }

        // PUT: api/PIZZAs/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutPIZZA(int id, PIZZA pIZZA)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != pIZZA.PIZZA_ID)
            {
                return BadRequest();
            }

            db.Entry(pIZZA).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!PIZZAExists(id))
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

        // POST: api/PIZZAs
        [ResponseType(typeof(PIZZA))]
        public IHttpActionResult PostPIZZA(PIZZA pIZZA)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.PIZZAs.Add(pIZZA);
            db.SaveChanges();

            return CreatedAtRoute("DefaultApi", new { id = pIZZA.PIZZA_ID }, pIZZA);
        }

        // DELETE: api/PIZZAs/5
        [ResponseType(typeof(PIZZA))]
        public IHttpActionResult DeletePIZZA(int id)
        {
            PIZZA pIZZA = db.PIZZAs.Find(id);
            if (pIZZA == null)
            {
                return NotFound();
            }

            db.PIZZAs.Remove(pIZZA);
            db.SaveChanges();

            return Ok(pIZZA);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool PIZZAExists(int id)
        {
            return db.PIZZAs.Count(e => e.PIZZA_ID == id) > 0;
        }
    }
}