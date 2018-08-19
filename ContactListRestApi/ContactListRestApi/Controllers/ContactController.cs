using ContactListRestApi.BLL;
using ContactListRestApi.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Cors;

namespace ContactListRestApi.Controllers
{
    [EnableCors(origins: "*", headers: "*", methods: "*")]
    public class ContactController : ApiController
    {


        ContactService service = new ContactService();



        [Route("contacts/")]
        [AcceptVerbs("GET")]
        public IHttpActionResult GetAll()
        {
            return Ok(service.GetAllContacts());
        }



        [Route("contact/{id}")]
        [AcceptVerbs("GET")]
        public IHttpActionResult Get(int id)
        {
            Contact found = service.GetContactById(id);

            if (found == null)
                return NotFound();

            return Ok(found);
        }



        [Route("contact/")]
        [AcceptVerbs("POST")]
        public void Add(Contact contact)
        {
            service.AddContact(contact);
        }



        [Route("contact/{id}")]
        [AcceptVerbs("PUT")]
        public void Update(int id, Contact contact)
        {
            service.UpdateContact(contact);
        }



        [Route("contact/{id}")]
        [AcceptVerbs("DELETE")]
        public void Delete(int id)
        {
            service.DeleteContact(id);
        }

    }
}