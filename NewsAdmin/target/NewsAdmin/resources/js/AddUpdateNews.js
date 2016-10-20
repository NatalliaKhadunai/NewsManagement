$(document).ready(function(){
    var today = new Date();
    var dd = today.getDate();
    if (dd < 10) dd = '0' + dd;
    var mm = today.getMonth() + 1;
    if (mm < 10) mm = '0' + mm;
    var yyyy = today.getFullYear();
    today = yyyy + '-' + mm + '-' + dd;
    $('#date').attr("max", today);
    $('#addNewsForm').submit(function(event) {
        if ($('#mainTitle').val().trim().length == 0) {
            $('#error').html('<p>Main Title shouldn\'t be empty!</p>');
            event.preventDefault();
        }
        if ($('#shortTitle').val().trim().length == 0) {
            $('#error').html('<p>Short Title shouldn\'t be empty!</p>');
            event.preventDefault();
        }
        if ($('#content').val().trim().length == 0) {
            $('#error').html('<p>Content shouldn\'t be empty!</p>');
            event.preventDefault();
        }
    });
});