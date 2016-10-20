$(document).ready(function(){
    $('button').click(function() {
        var id = $(this).attr('name');
        if ($('#' + id).is(':disabled') == true) $('#' + id).prop('disabled', false);
        else $('#' + id).prop('disabled', true);
        $('#' + 'update-delete-forms-' + id).toggle();
        var buttonId = $(this).attr('id');
        if ($('#' + buttonId).text() == 'Edit') $('#' + buttonId).text('Cancel');
        else $('#' + buttonId).text('Edit');
    });
    $('form[name=updateAuthorForm]').submit(function(event) {
        var id = $(this).find('input[name="authorId"]').val();
        var authorNewName = $('#' + id).val();
        var reg = /\w+\s\w+/i;
        if (!reg.test(authorNewName)) {
            $('#error').html('<p>Wrong author name!</p>');
            event.preventDefault();
        }
        else {
            var input = $("<input>")
                .attr('type', 'hidden')
                .attr('name', 'authorName').val(authorNewName);
            $('#updateAuthorForm').append($(input));
        }
    });
    $('#addAuthorForm').submit(function(event) {
       var reg = /\w+\s\w+/i;
        if (!reg.test($('#addAuthor').val())) {
            $('#error').html('<p>Wrong author name!</p>');
            event.preventDefault();
        }
    });
});
