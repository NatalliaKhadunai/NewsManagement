$(document).ready(function() {
   $('#tagList').change(function(){
       $('#tagName').val($("#tagList option:selected").text());
       $('#tagIdToUpdate').val($("#tagList option:selected").val());
       $('#tagIdToDelete').val($("#tagList option:selected").val());
   });
});
