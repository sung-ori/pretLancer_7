package com.team.pretLancer_7.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyPage {
    String memberid;        // 아이디
	String membername;      // 이름
	String membernick;      // 닉네임
	int mem_ex;             // 신뢰도 (경험치)
	String mem_level;       // 레벨 등급
	String memberlang;      // 사용 언어 정보
    String profilecomment;  // 소개문
    String originphoto;     // 원본 프로필 사진
    String savedphoto;      // 저장 프로필 사진
    
}
