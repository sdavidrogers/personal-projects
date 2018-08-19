function addContact() {
    $.ajax({
        type: 'POST',
        url: 'http://localhost:64202/contact',
        data: JSON.stringify({
            firstName: $('#add-first-name').val(),
            lastName: $('#add-last-name').val(),
            phoneNumber: $('#add-phone').val(),
            email: $('#add-email').val()
        }),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'dataType': 'json',
        success: function (data, status) {
            location.href = "http://localhost:64202/Views/contactList.html";
        }
    });
}