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
    public class DEAL_BRANCHController : ApiController
    {
        private Entities db = new Entities();

        // GET: api/DEAL_BRANCH
        public IQueryable<DEAL_BRANCH> GetDEAL_BRANCH()
        {
            return db.DEAL_BRANCH;
        }

        // GET: api/DEAL_BRANCH/5
        [ResponseType(typeof(DEAL_BRANCH))]
        public IHttpActionResult GetDEAL_BRANCH(int id)
        {
            DEAL_BRANCH dEAL_BRANCH = db.DEAL_BRANCH.Find(id);
            if (dEAL_BRANCH == null)
            {
                return NotFound();
            }

            return Ok(dEAL_BRANCH);
        }

        // PUT: api/DEAL_BRANCH/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutDEAL_BRANCH(int id, DEAL_BRANCH dEAL_BRANCH)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != dEAL_BRANCH.DEAL_ID)
            {
                return BadRequest();
            }

            db.Entry(dEAL_BRANCH).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!DEAL_BRANCHExists(id))
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

        // POST: api/DEAL_BRANCH
        [ResponseType(typeof(DEAL_BRANCH))]
        public IHttpActionResult PostDEAL_BRANCH(DEAL_BRANCH dEAL_BRANCH)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.DEAL_BRANCH.Add(dEAL_BRANCH);

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateException)
            {
                if (DEAL_BRANCHExists(dEAL_BRANCH.DEAL_ID))
                {
                    return Conflict();
                }
                else
                {
                    throw;
                }
            }

            return CreatedAtRoute("DefaultApi", new { id = dEAL_BRANCH.DEAL_ID }, dEAL_BRANCH);
        }

        // DELETE: api/DEAL_BRANCH/5
        [ResponseType(typeof(DEAL_BRANCH))]
        public IHttpActionResult DeleteDEAL_BRANCH(int id)
        {
            DEAL_BRANCH dEAL_BRANCH = db.DEAL_BRANCH.Find(id);
            if (dEAL_BRANCH == null)
            {
                return NotFound();
            }

            db.DEAL_BRANCH.Remove(dEAL_BRANCH);
            db.SaveChanges();

            return Ok(dEAL_BRANCH);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool DEAL_BRANCHExists(int id)
        {
            return db.DEAL_BRANCH.Count(e => e.DEAL_ID == id) > 0;
        }
    }
}