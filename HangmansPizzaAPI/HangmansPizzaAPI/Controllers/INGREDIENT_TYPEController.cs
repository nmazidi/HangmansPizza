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
    public class INGREDIENT_TYPEController : ApiController
    {
        private Entities db = new Entities();

        // GET: api/INGREDIENT_TYPE
        public IQueryable<INGREDIENT_TYPE> GetINGREDIENT_TYPE()
        {
            return db.INGREDIENT_TYPE;
        }

        // GET: api/INGREDIENT_TYPE/5
        [ResponseType(typeof(INGREDIENT_TYPE))]
        public IHttpActionResult GetINGREDIENT_TYPE(int id)
        {
            INGREDIENT_TYPE iNGREDIENT_TYPE = db.INGREDIENT_TYPE.Find(id);
            if (iNGREDIENT_TYPE == null)
            {
                return NotFound();
            }

            return Ok(iNGREDIENT_TYPE);
        }

        // PUT: api/INGREDIENT_TYPE/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutINGREDIENT_TYPE(int id, INGREDIENT_TYPE iNGREDIENT_TYPE)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != iNGREDIENT_TYPE.INGREDIENT_TYPE_ID)
            {
                return BadRequest();
            }

            db.Entry(iNGREDIENT_TYPE).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!INGREDIENT_TYPEExists(id))
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

        // POST: api/INGREDIENT_TYPE
        [ResponseType(typeof(INGREDIENT_TYPE))]
        public IHttpActionResult PostINGREDIENT_TYPE(INGREDIENT_TYPE iNGREDIENT_TYPE)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.INGREDIENT_TYPE.Add(iNGREDIENT_TYPE);
            db.SaveChanges();

            return CreatedAtRoute("DefaultApi", new { id = iNGREDIENT_TYPE.INGREDIENT_TYPE_ID }, iNGREDIENT_TYPE);
        }

        // DELETE: api/INGREDIENT_TYPE/5
        [ResponseType(typeof(INGREDIENT_TYPE))]
        public IHttpActionResult DeleteINGREDIENT_TYPE(int id)
        {
            INGREDIENT_TYPE iNGREDIENT_TYPE = db.INGREDIENT_TYPE.Find(id);
            if (iNGREDIENT_TYPE == null)
            {
                return NotFound();
            }

            db.INGREDIENT_TYPE.Remove(iNGREDIENT_TYPE);
            db.SaveChanges();

            return Ok(iNGREDIENT_TYPE);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool INGREDIENT_TYPEExists(int id)
        {
            return db.INGREDIENT_TYPE.Count(e => e.INGREDIENT_TYPE_ID == id) > 0;
        }
    }
}