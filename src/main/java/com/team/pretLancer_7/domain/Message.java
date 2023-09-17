package com.team.pretLancer_7.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    public String to; // 메세지를 발행하는 주체나 메세지를 생성한 주체
    public String from; // 메세지를 받는 대상
    public String message; // 실제 메세지 내용으로 화면에 띄워줄 내용
    public String checked; // 알림 칸을 확인했는지 여부
    public String click; // 클릭해서 알림을 실제 확인 했는지 여부
    public String args; // 링크 열 때 인자가 되는 요소

}
