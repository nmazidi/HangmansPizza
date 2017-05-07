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
    public class ORDERsController : ApiController
    {
        private Entities db = new Entities();

        // GET: api/ORDERs
        public IQueryable<ORDER> GetORDERS()
        {
            return db.ORDERS;
        }

        // GET: api/ORDERs/5
        [ResponseType(typeof(ORDER))]
        public IHttpActionResult GetORDER(int id)
        {
            ORDER oRDER = db.ORDERS.Find(id);
            if (oRDER == null)
            {
                return NotFound();
            }

            return Ok(oRDER);
        }

        // PUT: api/ORDERs/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutORDER(int id, ORDER oRDER)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != oRDER.ORDER_ID)
            {
                return BadRequest();
            }

            db.Entry(oRDER).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!ORDERExists(id))
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

        // POST: api/ORDERs
        [ResponseType(typeof(ORDER))]
        public IHttpActionResult PostORDER(ORDER oRDER)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.ORDERS.Add(oRDER);
            db.SaveChanges();

            return CreatedAtRoute("DefaultApi", new { id = oRDER.ORDER_ID }, oRDER);
        }

        // DELETE: api/ORDERs/5
        [ResponseType(typeof(ORDER))]
        public IHttpActionResult DeleteORDER(int id)
        {
            ORDER oRDER = db.ORDERS.Find(id);
            if (oRDER == null)
            {
                return NotFound();
            }

            db.ORDERS.Remove(oRDER);
            db.SaveChanges();

            return Ok(oRDER);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool ORDERExists(int id)
        {
            return db.ORDERS.Count(e => e.ORDER_ID == id) > 0;
        }
    }
}