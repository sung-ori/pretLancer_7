let socket = null;
$(document).ready(function() {

});

function connectWs() {
    //WebSocketConfig에서 설정한 endPoint ("/push")로 연결
    let ws = new SockJS("/push");
    socket = ws;

    ws.onopen = function() {
        console.log("open");
    };

    ws.onmessage = function(event) {
        console.log(event.data);
        let $socketAlert = $("#socketAlert");
        // EchoHandler 에서 설정한 메세지 넣어줌 
        $socketAlert.html(event.data)
        $socketAlert.css("display","block");

        // 일정 시간 지나면 알림 사라짐
        setTimeout(function() {
            $socketAlert.css('display', 'none');
        },5000);
    }
}