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
    public class PIZZA_INGREDIENTController : ApiController
    {
        private Entities db = new Entities();

        // GET: api/PIZZA_INGREDIENT
        public IQueryable<PIZZA_INGREDIENT> GetPIZZA_INGREDIENT()
        {
            return db.PIZZA_INGREDIENT;
        }

        // GET: api/PIZZA_INGREDIENT/5
        [ResponseType(typeof(PIZZA_INGREDIENT))]
        public IHttpActionResult GetPIZZA_INGREDIENT(int id)
        {
            PIZZA_INGREDIENT pIZZA_INGREDIENT = db.PIZZA_INGREDIENT.Find(id);
            if (pIZZA_INGREDIENT == null)
            {
                return NotFound();
            }

            return Ok(pIZZA_INGREDIENT);
        }

        // PUT: api/PIZZA_INGREDIENT/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutPIZZA_INGREDIENT(int id, PIZZA_INGREDIENT pIZZA_INGREDIENT)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != pIZZA_INGREDIENT.PIZZA_ID)
            {
                return BadRequest();
            }

            db.Entry(pIZZA_INGREDIENT).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!PIZZA_INGREDIENTExists(id))
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

        // POST: api/PIZZA_INGREDIENT
        [ResponseType(typeof(PIZZA_INGREDIENT))]
        public IHttpActionResult PostPIZZA_INGREDIENT(PIZZA_INGREDIENT pIZZA_INGREDIENT)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.PIZZA_INGREDIENT.Add(pIZZA_INGREDIENT);
            db.SaveChanges();

            return CreatedAtRoute("DefaultApi", new { id = pIZZA_INGREDIENT.PIZZA_ID }, pIZZA_INGREDIENT);
        }

        // DELETE: api/PIZZA_INGREDIENT/5
        [ResponseType(typeof(PIZZA_INGREDIENT))]
        public IHttpActionResult DeletePIZZA_INGREDIENT(int id)
        {
            PIZZA_INGREDIENT pIZZA_INGREDIENT = db.PIZZA_INGREDIENT.Find(id);
            if (pIZZA_INGREDIENT == null)
            {
                return NotFound();
            }

            db.PIZZA_INGREDIENT.Remove(pIZZA_INGREDIENT);
            db.SaveChanges();

            return Ok(pIZZA_INGREDIENT);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool PIZZA_INGREDIENTExists(int id)
        {
            return db.PIZZA_INGREDIENT.Count(e => e.PIZZA_ID == id) > 0;
        }
    }
}