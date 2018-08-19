using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace ContactListMVC.Models
{
    public class Contact
    {
        public int ContactID { get; set; }

        [Required(ErrorMessage = "Must enter a first name.")]
        [Display(Name = "First Name")]
        [MaxLength(30, ErrorMessage = "Max length is 30 characters.")]
        public string FirstName { get; set; }

        [Required(ErrorMessage = "Must enter a last name.")]
        [Display(Name = "Last Name")]
        [MaxLength(30, ErrorMessage = "Max length is 30 characters.")]
        public string LastName { get; set; }

        [Required(ErrorMessage = "Must enter a phone number.")]
        [Display(Name = "Phone Number")]
        [RegularExpression(@"^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$", 
            ErrorMessage = "Enter a valid phone number.")]
        public string PhoneNumber { get; set; }

        [Required(ErrorMessage = "Must enter an email address.")]
        [MaxLength(50, ErrorMessage = "Max length is 50 characters.")]
        public string Email { get; set; }
    }
}