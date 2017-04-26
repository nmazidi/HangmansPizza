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
    public class MENU_ITEMController : ApiController
    {
        private Entities5 db = new Entities5();

        // GET: api/MENU_ITEM
        public IQueryable<MENU_ITEM> GetMENU_ITEM()
        {
            return db.MENU_ITEM;
        }

        // GET: api/MENU_ITEM/5
        [ResponseType(typeof(MENU_ITEM))]
        public IHttpActionResult GetMENU_ITEM(int id)
        {
            MENU_ITEM mENU_ITEM = db.MENU_ITEM.Find(id);
            if (mENU_ITEM == null)
            {
                return NotFound();
            }

            return Ok(mENU_ITEM);
        }

        // PUT: api/MENU_ITEM/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutMENU_ITEM(int id, MENU_ITEM mENU_ITEM)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != mENU_ITEM.MENU_ID)
            {
                return BadRequest();
            }

            db.Entry(mENU_ITEM).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!MENU_ITEMExists(id))
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

        // POST: api/MENU_ITEM
        [ResponseType(typeof(MENU_ITEM))]
        public IHttpActionResult PostMENU_ITEM(MENU_ITEM mENU_ITEM)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.MENU_ITEM.Add(mENU_ITEM);

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateException)
            {
                if (MENU_ITEMExists(mENU_ITEM.MENU_ID))
                {
                    return Conflict();
                }
                else
                {
                    throw;
                }
            }

            return CreatedAtRoute("DefaultApi", new { id = mENU_ITEM.MENU_ID }, mENU_ITEM);
        }

        // DELETE: api/MENU_ITEM/5
        [ResponseType(typeof(MENU_ITEM))]
        public IHttpActionResult DeleteMENU_ITEM(int id)
        {
            MENU_ITEM mENU_ITEM = db.MENU_ITEM.Find(id);
            if (mENU_ITEM == null)
            {
                return NotFound();
            }

            db.MENU_ITEM.Remove(mENU_ITEM);
            db.SaveChanges();

            return Ok(mENU_ITEM);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool MENU_ITEMExists(int id)
        {
            return db.MENU_ITEM.Count(e => e.MENU_ID == id) > 0;
        }
    }
}