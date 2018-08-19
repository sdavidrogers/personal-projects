$(document).ready(function () {

    fillEditForm();

});



function editContact() {
    $.ajax({
        type: 'PUT',
        url: 'http://localhost:64202/contact/' + $('#edit-contact-id').val(),
        data: JSON.stringify({
            contactID: $('#edit-contact-id').val(),
            firstName: $('#edit-first-name').val(),
            lastName: $('#edit-last-name').val(),
            phoneNumber: $('#edit-phone').val(),
            email: $('#edit-email').val()
        }),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'dataType': 'json',
        success: function () {
            location.href = "http://localhost:64202/Views/contactList.html";
        }
    });
}



function fillEditForm() {
    var contactId = localStorage.getItem("editID");
    $.ajax({
        type: 'GET',
        url: 'http://localhost:64202/contact/' + contactId,
        success: function (data, status) {
            $('#edit-first-name').val(data.firstName);
            $('#edit-last-name').val(data.lastName);
            $('#edit-phone').val(data.phoneNumber);
            $('#edit-email').val(data.email);
            $('#edit-contact-id').val(data.contactID);
        }
    });
}