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
    public class ITEMsController : ApiController
    {
        private Entities5 db = new Entities5();

        // GET: api/ITEMs
        public IQueryable<ITEM> GetITEMs()
        {
            return db.ITEMs;
        }

        // GET: api/ITEMs/5
        [ResponseType(typeof(ITEM))]
        public IHttpActionResult GetITEM(int id)
        {
            ITEM iTEM = db.ITEMs.Find(id);
            if (iTEM == null)
            {
                return NotFound();
            }

            return Ok(iTEM);
        }

        // PUT: api/ITEMs/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutITEM(int id, ITEM iTEM)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != iTEM.ITEM_ID)
            {
                return BadRequest();
            }

            db.Entry(iTEM).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!ITEMExists(id))
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

        // POST: api/ITEMs
        [ResponseType(typeof(ITEM))]
        public IHttpActionResult PostITEM(ITEM iTEM)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.ITEMs.Add(iTEM);
            db.SaveChanges();

            return CreatedAtRoute("DefaultApi", new { id = iTEM.ITEM_ID }, iTEM);
        }

        // DELETE: api/ITEMs/5
        [ResponseType(typeof(ITEM))]
        public IHttpActionResult DeleteITEM(int id)
        {
            ITEM iTEM = db.ITEMs.Find(id);
            if (iTEM == null)
            {
                return NotFound();
            }

            db.ITEMs.Remove(iTEM);
            db.SaveChanges();

            return Ok(iTEM);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool ITEMExists(int id)
        {
            return db.ITEMs.Count(e => e.ITEM_ID == id) > 0;
        }
    }
}