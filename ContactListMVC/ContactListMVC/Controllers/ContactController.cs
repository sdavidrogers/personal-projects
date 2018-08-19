using ContactListMVC.Models;
using ContactListMVC.Models.Service;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace ContactListMVC.Controllers
{
    public class ContactController : Controller
    {


        ContactService service = new ContactService();



        // GET: Contact
        public ActionResult Index()
        {
            return View(service.GetAllContactsOrderByName());
        }



        public ActionResult DisplayAddContact()
        {
            return View("~/Views/Contact/AddContact.cshtml");
        }



        public ActionResult AddContact(Contact contact)
        {
            if (ModelState.IsValid)
            {
                service.AddContact(contact);
                return RedirectToAction("Index");
            }
            else
            {
                return View("AddContact", contact);
            }
        }



        public ActionResult DeleteContact(int id)
        {
            service.DeleteContact(id);
            return RedirectToAction("Index");
        }



        public ActionResult DisplayEditContact(int id)
        {
            return View("~/Views/Contact/EditContact.cshtml", service.GetContactById(id));
        }



        public ActionResult EditContact(Contact contact)
        {
            if (ModelState.IsValid)
            {
                service.UpdateContact(contact);
                return RedirectToAction("Index");
            }
            else
            {
                return View("EditContact", contact);
            }
        }



    }
}