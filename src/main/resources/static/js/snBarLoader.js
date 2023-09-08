/**
 * 
 */

 $(document).ready(function() {
        $.ajax({
    url: "/pretLancer/snBar", // sidebar로 연결함
    success: function(data) {
        $("#snBar-placeholder").html(data);
    }
});

});