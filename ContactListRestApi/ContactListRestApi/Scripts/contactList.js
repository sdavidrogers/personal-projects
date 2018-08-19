$(document).ready(function () {

    loadContacts();

});



function loadContacts() {
    clearContactTable();

    var contentRows = $("#contentRows");

    $.ajax({
        type: 'GET',
        url: 'http://localhost:64202/contacts',
        success: function (data, status) {
            $.each(data, function (index, contact) {
                var name = contact.lastName + ', ' + contact.firstName;
                var phone = contact.phoneNumber;
                var email = contact.email;
                var id = contact.contactID;

                var row = '<tr id="tableRow" class="text-center">';
                row += '<td>' + name + '</td>';
                row += '<td>' + phone + '</td>';
                row += '<td>' + email + '</td>';
                row += '<td><a onclick="displayEditPage(' + id + ')">Edit</a></td>';
                row += '<td><a onclick="deleteContact(' + id + ')">Delete</a></td>';
                row += '</tr>';
                contentRows.append(row);
            });
        },
        error: function () {
            $('#errorMessages')
                .append($('<li>')
                    .attr({ class: 'list-group-item list-group-item-danger' })
                    .text('Error calling web service.  Please try again later.'));
        }
    });
}



function clearContactTable() {
    $('#contentRows').empty();
}



function deleteContact(contactId) {
    $.ajax({
        type: 'DELETE',
        url: "http://localhost:64202/contact/" + contactId,
        success: function (status) {
            loadContacts();
        }
    });
}



function displayEditPage(contactId) {
    localStorage.setItem("editID", contactId);
    location.href = "http://localhost:64202/Views/editContact.html";
}



function displayAddPage() {
    location.href = "http://localhost:64202/Views/addContact.html";
}