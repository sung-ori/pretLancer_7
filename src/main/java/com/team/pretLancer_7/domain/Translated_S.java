package com.team.pretLancer_7.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Translated_S {
	
	int translatednum_s;			// 번역 고유 번호
	int requestnum_s;				// 단문 의뢰 번호
	String memberid;				// 번역자 아이디
	String requestcontent_s;		// 번역요청내용
	String translatedcontent_s;		// 번역결과내용
	String comment_st;				// 비고

}
