
    $(document).ready(function() {
        
        let bdt = $("#boardTitle").text();
        let bdn = $("#boardNum").val();
        let wId = $("#writerId").val();
        let lId = $("#loginId").val();

        let header = $("meta[name='_csrf_header']").attr('content');
        let token = $("meta[name='_csrf']").attr('content');
        
        readReply();
        readInfo();
        policeCount();
        $("#rBt").click(writeReply);
        
    
        $(document).on("click", "#polBt", checkPolice);

        $(document).on("click", ".replyRecoBt", replyReco);
        $(document).on("click", ".dBt", deleteReply);
        
        $(document).on("click", "#recoBt", reco);
        $(document).on("click", "#decoBt", deco);
        // $("#recoBt").click(recoUp);

   
        function writeReply() {

            let reply = $("#inputReply").val();
            $.ajax({
                type:"POST",
                url: "insertReply",
                // beforeSend: function(xhr){
                //     xhr.setRequestHeader(header, token);
                // },
                data: {"replycontent" : reply ,
                        "boardnum" : bdn },
                success: function() {
                    alert("댓글 작성 성공");
                    $("#inputReply").val("");
                    readReply();
                    if (lId != wId) {
                        if (socket) {
                            let socketMsg = "reply,"+lId+","+wId+","+bnd+bdt;
                            socket.send(socketMsg);
                        }
                    }
                },
                error: function(e) {
                    console.log(JSON.stringify(e));
                }
            });
        } // End OF write

        function readReply() {
            $.ajax({
                type:"GET",
                url: "readReply",
                data: {"boardnum" : bdn },
                dataType: "json",
                success: function(list) {
                    alert("댓글 읽어오기 성공!");
                    let str = `<table>
                                <tr>
                                <td>작성자</td>
                                <td>댓글 내용</td>
                                <td>작성 시간</td>
                                <td>추천 수</td>
                                <td></td>
                                </tr>`;
                    
                    $.each(list,function(i,n){
                        str += `<tr>
                            <td>${n.memberid}</td>
                            <td>${n.replycontent}</td>
                            <td>${n.replydate}</td>
                            <td><input type="button" id="${n.replynum}" class="replyRecoBt" value="👍"></td>
                            <td>${n.replyrecommend}</td>`;
                        
                        // 댓글 다 만들고 안보이게 만든다.
                        str += `<td>
                
                                <span style="display: none;" class="${n.memberid}">
                                <input type="button" id="${n.replynum}" class="dBt" value="삭제">
                                </span>
                                </td></tr>`;
                    });
                    str += "</table>";
                    $("#replyBox").html(str);
                    buttonDisplay();

                },
                error: function(e) {
                    alert("댓글 못 불러옴")
                    console.log(JSON.stringify(e));
                }
            });
        }// End Of read reply

        function deleteReply() {
            let r = $(this).attr("id");
            let n = $("#bdn").val();
            console.log(r);
            console.log(n);
            $.ajax({
                url:"deleteReply",
                type:"get",
                data: {replynum: r},
                success: function(rst){
                    alert("삭제되었습니다.");
                    readReply();
                },
                error: function(e) {
                    alert(JSON.stringify(e));
                }
            });
        }
        // 댓글 주인이면 글 버튼 보이게 한다.
		function buttonDisplay() {
            let lid = $("#loginId").val();
            console.log(lid)
            $('.'+lid).show();
        }

        function readInfo() {

            $.ajax({
                type:"GET",
                url:"readInfo",
                data:{"boardnum" : bdn},
                dataType:"json",
                success: function(board) {
                    let str = ` 
                        🧚‍♀️
                        <span>${board.memberid}</span>
                        🕰️
                        <span>${board.inputdate}</span>
                        👁️
                        <span>${board.hits}</span>
                        <input type="button" id="recoBt" value="👍" class="bt">
                        <span>${board.recommend}</span>
                        <input type="button" id="decoBt" value="👎" class="bt">
                        <span>${board.decommend}</span>
                        <input type="button" id="polBt" value="🚨" class="bt">
                        <span>${board.police}</span>`;

                        $("#info").html(str);

                        btColorize();
                },
                error: function(e) {

                }
            })
        }

        function reco(){

            $.ajax({
                type: "GET",
                url : "recommendUp",
                data:{"boardnum" : bdn},
                success: function(color) {
                    readInfo();
                },
                error: function(e) {
                    console.log(JSON.stringify(e));
                }

            });
        }
        function deco(){

            $.ajax({
                type: "GET",
                url : "decommendUp",
                data:{"boardnum" : bdn},
                success: function() {
                    readInfo();
                },
                error: function(e) {
                    console.log(JSON.stringify(e));
                }

            });
        }

        function btColorize() {
            $.ajax({
                type: "GET",
                url : "colorize",
                data:{"boardnum" : bdn},
                success: function(bt) {
                    if (bt == "") {
                        $(".bt").css("background-color","none");
                    }else{
                        $('#'+bt).css("background-color","red");
                    }
                },
                error: function(e) {
                    console.log(JSON.stringify(e));
                }

            });
        }

        function replyReco() {
            let replynum = $(this).attr("id");
            alert(replynum);
            $.ajax({
                type: "get",
                url: "replyRecommend",
                data: {"replynum" : replynum},
                success: function() {
                    readReply();
                },
                error: function(e) {
                    alert(JSON.stringify(e));
                }
            });
        }

        function policeForm() {
            let = childForm = window.open('policeForm', '신고?', 'width=350,height=400,top=200,left=500,maximizable=false');
            
        }

        function checkPolice() {

            if(lId == wId) {
                alert("자신의 글은 신고할 수 없습니다.");
                return;
            }
            
            $.ajax({
                type:"get",
                url:"policeCheck",
                data:{"boardnum" : bdn,},
                dataType:"text",
                success: function(data) {
                    if(data == "true"){
                        confirm("신고는 취소가 불가능합니다. 정말 취소하시겠습니까?");
                        policeForm();
                    }else{
                        alert("이미 신고 하셨습니다.");
                    }
                },
                error: function(e) {
                    alert(JSON.stringify(e));
                }
            });
        }
        
        function policeCount() {
            $.ajax({
                type: "get",
                url: "policeCount",
                data:{"boardnum" : bdn},
                dataType:"text",
                success: function(rst) {

                    if(rst == "true"){
                        alert("삭제된 게시물 입니다.");
                        location.href="main"
                    }else{

                    }
                },
                error: function(e) {
                    alert(JSON.stringify(e));
                }
            });
        }
    });// End Of jQuery