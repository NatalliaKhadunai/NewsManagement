$(document).ready(function() {
   $('#loginForm').submit(function(event) {
       if ($('#login').val().trim().length == 0) {
           $('#error').html('<p>Wrong login!</p>');
           event.preventDefault();
       }
       if ($('#password').val().trim().length == 0) {
           $('#error').html('<p>Wrong password!</p>');
           event.preventDefault();
       }
   });
});
