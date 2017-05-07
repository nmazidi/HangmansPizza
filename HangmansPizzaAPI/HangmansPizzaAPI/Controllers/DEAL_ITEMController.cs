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
    public class DEAL_ITEMController : ApiController
    {
        private Entities db = new Entities();

        // GET: api/DEAL_ITEM
        public IQueryable<DEAL_ITEM> GetDEAL_ITEM()
        {
            return db.DEAL_ITEM;
        }

        // GET: api/DEAL_ITEM/5
        [ResponseType(typeof(DEAL_ITEM))]
        public IHttpActionResult GetDEAL_ITEM(int id)
        {
            DEAL_ITEM dEAL_ITEM = db.DEAL_ITEM.Find(id);
            if (dEAL_ITEM == null)
            {
                return NotFound();
            }

            return Ok(dEAL_ITEM);
        }

        // PUT: api/DEAL_ITEM/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutDEAL_ITEM(int id, DEAL_ITEM dEAL_ITEM)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != dEAL_ITEM.DEAL_ID)
            {
                return BadRequest();
            }

            db.Entry(dEAL_ITEM).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!DEAL_ITEMExists(id))
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

        // POST: api/DEAL_ITEM
        [ResponseType(typeof(DEAL_ITEM))]
        public IHttpActionResult PostDEAL_ITEM(DEAL_ITEM dEAL_ITEM)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.DEAL_ITEM.Add(dEAL_ITEM);

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateException)
            {
                if (DEAL_ITEMExists(dEAL_ITEM.DEAL_ID))
                {
                    return Conflict();
                }
                else
                {
                    throw;
                }
            }

            return CreatedAtRoute("DefaultApi", new { id = dEAL_ITEM.DEAL_ID }, dEAL_ITEM);
        }

        // DELETE: api/DEAL_ITEM/5
        [ResponseType(typeof(DEAL_ITEM))]
        public IHttpActionResult DeleteDEAL_ITEM(int id)
        {
            DEAL_ITEM dEAL_ITEM = db.DEAL_ITEM.Find(id);
            if (dEAL_ITEM == null)
            {
                return NotFound();
            }

            db.DEAL_ITEM.Remove(dEAL_ITEM);
            db.SaveChanges();

            return Ok(dEAL_ITEM);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool DEAL_ITEMExists(int id)
        {
            return db.DEAL_ITEM.Count(e => e.DEAL_ID == id) > 0;
        }
    }
}