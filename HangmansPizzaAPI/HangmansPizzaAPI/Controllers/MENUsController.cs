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
    public class MENUsController : ApiController
    {
        private Entities db = new Entities();

        // GET: api/MENUs
        public IQueryable<MENU> GetMENUs()
        {
            return db.MENUs;
        }

        // GET: api/MENUs/5
        [ResponseType(typeof(MENU))]
        public IHttpActionResult GetMENU(int id)
        {
            MENU mENU = db.MENUs.Find(id);
            if (mENU == null)
            {
                return NotFound();
            }

            return Ok(mENU);
        }

        // PUT: api/MENUs/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutMENU(int id, MENU mENU)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != mENU.MENU_ID)
            {
                return BadRequest();
            }

            db.Entry(mENU).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!MENUExists(id))
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

        // POST: api/MENUs
        [ResponseType(typeof(MENU))]
        public IHttpActionResult PostMENU(MENU mENU)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.MENUs.Add(mENU);
            db.SaveChanges();

            return CreatedAtRoute("DefaultApi", new { id = mENU.MENU_ID }, mENU);
        }

        // DELETE: api/MENUs/5
        [ResponseType(typeof(MENU))]
        public IHttpActionResult DeleteMENU(int id)
        {
            MENU mENU = db.MENUs.Find(id);
            if (mENU == null)
            {
                return NotFound();
            }

            db.MENUs.Remove(mENU);
            db.SaveChanges();

            return Ok(mENU);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool MENUExists(int id)
        {
            return db.MENUs.Count(e => e.MENU_ID == id) > 0;
        }
    }
}