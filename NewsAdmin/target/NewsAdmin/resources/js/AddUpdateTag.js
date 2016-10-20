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
    $('form[name=updateTagForm]').submit(function(event) {
        var id = $(this).find('input[name="tagId"]').val();
        var tagNewName = $('#' + id).val();
        var reg = /\w+/i;
        if (!reg.test(tagNewName)) {
            $('#error').html('<p>Wrong tag name!</p>');
            event.preventDefault();
        }
        else {
            var input = $("<input>")
                .attr('type', 'hidden')
                .attr('name', 'tagName').val(tagNewName);
            $('#updateTagForm').append($(input));
        }
    });
    $('#addTagForm').submit(function(event) {
        var reg = /\w+/i;
        if (!reg.test($('#addTag').val())) {
            $('#error').html('<p>Wrong tag name!</p>');
            event.preventDefault();
        }
    });
});
