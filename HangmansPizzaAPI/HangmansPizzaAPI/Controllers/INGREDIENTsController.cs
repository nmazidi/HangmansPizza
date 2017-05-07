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
    public class INGREDIENTsController : ApiController
    {
        private Entities db = new Entities();

        // GET: api/INGREDIENTs
        public IQueryable<INGREDIENT> GetINGREDIENTs()
        {
            return db.INGREDIENTs;
        }

        // GET: api/INGREDIENTs/5
        [ResponseType(typeof(INGREDIENT))]
        public IHttpActionResult GetINGREDIENT(int id)
        {
            INGREDIENT iNGREDIENT = db.INGREDIENTs.Find(id);
            if (iNGREDIENT == null)
            {
                return NotFound();
            }

            return Ok(iNGREDIENT);
        }

        // PUT: api/INGREDIENTs/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutINGREDIENT(int id, INGREDIENT iNGREDIENT)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != iNGREDIENT.INGREDIENT_ID)
            {
                return BadRequest();
            }

            db.Entry(iNGREDIENT).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!INGREDIENTExists(id))
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

        // POST: api/INGREDIENTs
        [ResponseType(typeof(INGREDIENT))]
        public IHttpActionResult PostINGREDIENT(INGREDIENT iNGREDIENT)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.INGREDIENTs.Add(iNGREDIENT);
            db.SaveChanges();

            return CreatedAtRoute("DefaultApi", new { id = iNGREDIENT.INGREDIENT_ID }, iNGREDIENT);
        }

        // DELETE: api/INGREDIENTs/5
        [ResponseType(typeof(INGREDIENT))]
        public IHttpActionResult DeleteINGREDIENT(int id)
        {
            INGREDIENT iNGREDIENT = db.INGREDIENTs.Find(id);
            if (iNGREDIENT == null)
            {
                return NotFound();
            }

            db.INGREDIENTs.Remove(iNGREDIENT);
            db.SaveChanges();

            return Ok(iNGREDIENT);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool INGREDIENTExists(int id)
        {
            return db.INGREDIENTs.Count(e => e.INGREDIENT_ID == id) > 0;
        }
    }
}