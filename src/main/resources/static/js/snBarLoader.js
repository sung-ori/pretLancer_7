/**
 * 
 */

 $(document).ready(function() {
        $.ajax({
    url: "/pretLancer/snBar", // sidebar로 연결함\
    type:"GET",
    success: function(data) {
        $("#snBar-placeholder").html(data);
    }
});

});