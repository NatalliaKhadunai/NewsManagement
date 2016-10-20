$(document).ready(function() {
   $("#authorList").change(function() {
      $("#authorIdToUpdate").val($("#authorList option:selected").val());
       $("#authorIdToDelete").val($("#authorList option:selected").val());
       searchAjax();
   });
});

function searchAjax() {
    $.ajax(
        {
            url: '/NewsAdmin/getAuthor',
            type: 'GET',
            data: {authorId: $("#authorIdToUpdate").val()},
            dataType: 'json',
            success: function(data) {
                $('#authorFirstName').val(data.firstName);
                $('#authorLastName').val(data.lastName);
            },
            error: function(data, status) {
                alert("ERROR: " + data + " " + status);
            }
        });
}