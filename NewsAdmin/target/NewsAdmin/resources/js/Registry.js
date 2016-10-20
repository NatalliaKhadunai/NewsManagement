$(document).ready(function() {
    $('#registryForm').submit(function(event) {
        if ($('#login').val().trim().length == 0) {
            $('#error').html('<p>Wrong login!</p>');
            event.preventDefault();
        }
        if ($('#password').val().trim().length == 0) {
            $('#error').html('<p>Wrong password!</p>');
            event.preventDefault();
        }
        if ($('#confirmPassword').val().trim().length == 0) {
            $('#error').html('<p>Wrong password confirm!</p>');
            event.preventDefault();
        }
        if ($('#password').val() != $('#confirmPassword').val()) {
            $('#error').html('<p>Passwords do not match</p>');
            event.preventDefault();
        }
    });
});

