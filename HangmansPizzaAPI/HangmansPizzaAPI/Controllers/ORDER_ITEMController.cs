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
    public class ORDER_ITEMController : ApiController
    {
        private Entities5 db = new Entities5();

        // GET: api/ORDER_ITEM
        public IQueryable<ORDER_ITEM> GetORDER_ITEM()
        {
            return db.ORDER_ITEM;
        }

        // GET: api/ORDER_ITEM/5
        [ResponseType(typeof(ORDER_ITEM))]
        public IHttpActionResult GetORDER_ITEM(int id)
        {
            ORDER_ITEM oRDER_ITEM = db.ORDER_ITEM.Find(id);
            if (oRDER_ITEM == null)
            {
                return NotFound();
            }

            return Ok(oRDER_ITEM);
        }

        // PUT: api/ORDER_ITEM/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutORDER_ITEM(int id, ORDER_ITEM oRDER_ITEM)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != oRDER_ITEM.ITEM_ID)
            {
                return BadRequest();
            }

            db.Entry(oRDER_ITEM).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!ORDER_ITEMExists(id))
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

        // POST: api/ORDER_ITEM
        [ResponseType(typeof(ORDER_ITEM))]
        public IHttpActionResult PostORDER_ITEM(ORDER_ITEM oRDER_ITEM)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.ORDER_ITEM.Add(oRDER_ITEM);

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateException)
            {
                if (ORDER_ITEMExists(oRDER_ITEM.ITEM_ID))
                {
                    return Conflict();
                }
                else
                {
                    throw;
                }
            }

            return CreatedAtRoute("DefaultApi", new { id = oRDER_ITEM.ITEM_ID }, oRDER_ITEM);
        }

        // DELETE: api/ORDER_ITEM/5
        [ResponseType(typeof(ORDER_ITEM))]
        public IHttpActionResult DeleteORDER_ITEM(int id)
        {
            ORDER_ITEM oRDER_ITEM = db.ORDER_ITEM.Find(id);
            if (oRDER_ITEM == null)
            {
                return NotFound();
            }

            db.ORDER_ITEM.Remove(oRDER_ITEM);
            db.SaveChanges();

            return Ok(oRDER_ITEM);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool ORDER_ITEMExists(int id)
        {
            return db.ORDER_ITEM.Count(e => e.ITEM_ID == id) > 0;
        }
    }
}