package com.team.pretLancer_7.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    
    public int messagenum; // 시퀀스
    public String type; // 메세지의 타입을 명명
    public String to; // 메세지를 발행하는 주체나 메세지를 생성한 주체
    public String from; // 메세지를 받는 대상
    public String message; // 실제 메세지 내용으로 화면에 띄워줄 내용
    public String checked; // 알림 칸을 확인했는지 여부
    public String clicked; // 클릭해서 알림을 실제 확인 했는지 여부
    public String href; // 링크 열 때 인자가 되는 요소


    // MP : 마이프로필 작성     args: userid
    // MT : 튜토리얼 하세요     args: 

    // LU : 레벨 업 안내       args:

    // CC : 댓글 달림                   args: boardnum
    // CP : 신고 누적당해서 글이 삭제당함    args: 

    // SE : 단문 번역 완료, 평가 시작                      args: 
    // SC : 번역 평가 완료, 성공 요청자에게 돌아감            args: requestnum_s
    // SF : 번역 평가 완료, 실패 번역가에게 결과를 알려줌       args: translatenum_s

    // ME : 중문 번역 완료, 평가 시작                   args:
    // MC : 번역 평가 완료, 성공 요청자에게 돌아감          args: requestnum_M
    // MF : 번역 평가 완료, 실패 번역가에게 결과를 알려줌       args: translatenum_M

    // LR : 장문 요청이 내게 옴.            args: userid
    // LA : 내가 보낸 요청이 수락받음         args: 채팅이 안되면 페이지 필요함. 
    // LF : 내가 보낸 요청이 거절당함           args: requestnum_l 내용 가지고 재요청 할 수 있게끔 처리
    // LB : 내가 입찰한 경매, 낙찰받음          args: 이것도 채팅 없으면 주고받을 페이지 필요.
    // LC : 장문 번역이 완료 됨 (채팅으로 받으면 상관 없지만 그거 안되면 페이지가 필요.)


}
