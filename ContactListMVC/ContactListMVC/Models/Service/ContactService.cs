using ContactListMVC.Models.DAO;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ContactListMVC.Models.Service
{
    public class ContactService
    {


        ContactDao dao = new ContactDao();



        public void AddContact(Contact contact)
        {
            dao.AddConntact(contact);
        }



        public void DeleteContact(int contactId)
        {
            dao.DeleteContact(contactId);
        }



        public void UpdateContact(Contact contact)
        {
            dao.UpdateContact(contact);
        }



        public List<Contact> GetAllContacts()
        {
            return dao.GetAllContacts();
        }



        public List<Contact> GetAllContactsOrderByName()
        {
            return dao.GetAllContactsOrderByName();
        }



        public Contact GetContactById(int contactId)
        {
            return dao.GetContactById(contactId);
        }



    }
}