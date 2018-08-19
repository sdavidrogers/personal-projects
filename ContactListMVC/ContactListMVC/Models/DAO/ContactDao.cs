using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Data.SqlClient;
using System.Configuration;

namespace ContactListMVC.Models.DAO
{
    public class ContactDao
    {


        public void AddConntact(Contact contact)
        {
            using (SqlConnection conn = new SqlConnection())
            {
                conn.ConnectionString = ConfigurationManager.ConnectionStrings["ContactList"].ConnectionString;

                SqlCommand cmd = new SqlCommand();
                cmd.Connection = conn;
                cmd.CommandText = "INSERT INTO Contacts (FirstName, LastName, PhoneNumber, Email) " +
                    "VALUES (@FirstName, @LastName, @Phone, @Email)";

                cmd.Parameters.AddWithValue("FirstName", contact.FirstName);
                cmd.Parameters.AddWithValue("LastName", contact.LastName);
                cmd.Parameters.AddWithValue("Phone", contact.PhoneNumber);
                cmd.Parameters.AddWithValue("Email", contact.Email);

                conn.Open();
                cmd.ExecuteNonQuery();
            }
        }



        public void DeleteContact(int contactId)
        {
            using (SqlConnection conn = new SqlConnection())
            {
                conn.ConnectionString = ConfigurationManager.ConnectionStrings["ContactList"].ConnectionString;

                SqlCommand cmd = new SqlCommand();
                cmd.Connection = conn;
                cmd.CommandText = "DELETE FROM Contacts " +
                    "WHERE ContactID = @ContactID";

                cmd.Parameters.AddWithValue("ContactID", contactId);

                conn.Open();
                cmd.ExecuteNonQuery();
            }
        }



        public void UpdateContact(Contact contact)
        {
            using (SqlConnection conn = new SqlConnection())
            {
                conn.ConnectionString = ConfigurationManager.ConnectionStrings["ContactList"].ConnectionString;

                SqlCommand cmd = new SqlCommand();
                cmd.Connection = conn;
                cmd.CommandText = "UPDATE Contacts " +
                    "SET FirstName = @FirstName, " +
                    "LastName = @LastName, " +
                    "PhoneNumber = @PhoneNumber, " +
                    "Email = @Email " +
                    "WHERE ContactID = @ContactID";

                cmd.Parameters.AddWithValue("FirstName", contact.FirstName);
                cmd.Parameters.AddWithValue("LastName", contact.LastName);
                cmd.Parameters.AddWithValue("PhoneNumber", contact.PhoneNumber);
                cmd.Parameters.AddWithValue("Email", contact.Email);
                cmd.Parameters.AddWithValue("ContactID", contact.ContactID);

                conn.Open();
                cmd.ExecuteNonQuery();
            }
        }



        public List<Contact> GetAllContacts()
        {
            List<Contact> contacts = new List<Contact>();

            using (SqlConnection conn = new SqlConnection())
            {
                conn.ConnectionString = ConfigurationManager.ConnectionStrings["ContactList"].ConnectionString;

                SqlCommand cmd = new SqlCommand();
                cmd.Connection = conn;
                cmd.CommandText = "SELECT * FROM Contacts";

                conn.Open();
                using (SqlDataReader dr = cmd.ExecuteReader())
                {
                    while (dr.Read())
                    {
                        Contact currentRow = new Contact();

                        currentRow.ContactID = (int) dr["ContactID"];
                        currentRow.FirstName = dr["FirstName"].ToString();
                        currentRow.LastName = dr["LastName"].ToString();
                        currentRow.PhoneNumber = dr["PhoneNumber"].ToString();
                        currentRow.Email = dr["Email"].ToString();

                        contacts.Add(currentRow);
                    }
                }
            }

            return contacts;
        }



        public List<Contact> GetAllContactsOrderByName()
        {
            List<Contact> contacts = new List<Contact>();

            using (SqlConnection conn = new SqlConnection())
            {
                conn.ConnectionString = ConfigurationManager.ConnectionStrings["ContactList"].ConnectionString;

                SqlCommand cmd = new SqlCommand();
                cmd.Connection = conn;
                cmd.CommandText = "SELECT * FROM Contacts " +
                    "ORDER BY LastName";

                conn.Open();
                using (SqlDataReader dr = cmd.ExecuteReader())
                {
                    while (dr.Read())
                    {
                        Contact currentRow = new Contact();

                        currentRow.ContactID = (int)dr["ContactID"];
                        currentRow.FirstName = dr["FirstName"].ToString();
                        currentRow.LastName = dr["LastName"].ToString();
                        currentRow.PhoneNumber = dr["PhoneNumber"].ToString();
                        currentRow.Email = dr["Email"].ToString();

                        contacts.Add(currentRow);
                    }
                }
            }

            return contacts;
        }



        public Contact GetContactById(int contactId)
        {
            Contact contact = new Contact();

            using (SqlConnection conn = new SqlConnection())
            {
                conn.ConnectionString = ConfigurationManager.ConnectionStrings["ContactList"].ConnectionString;

                SqlCommand cmd = new SqlCommand();
                cmd.Connection = conn;
                cmd.CommandText = "SELECT * FROM Contacts " +
                    "WHERE ContactID = @ContactID";

                cmd.Parameters.AddWithValue("ContactID", contactId);

                conn.Open();
                using (SqlDataReader dr = cmd.ExecuteReader())
                {
                    while (dr.Read())
                    {
                        contact.ContactID = (int) dr["ContactID"];
                        contact.FirstName = dr["FirstName"].ToString();
                        contact.LastName = dr["LastName"].ToString();
                        contact.PhoneNumber = dr["PhoneNumber"].ToString();
                        contact.Email = dr["Email"].ToString();
                    }
                }
            }

            return contact;
        }



    }
}