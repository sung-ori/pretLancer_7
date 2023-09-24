package com.team.pretLancer_7.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Request_L {

    int requestnum_l;	        // 장문 의뢰번호 시퀀스 PRET_REQ_L_SEQ.NEXTVAL

	String memberid;	        // 의뢰자 아이디
	String memberid2;	        // 번역자 아이디

	String category;            // 카테고리 (도메인 지정) / 논문, 

	String title;				// 경매 요청의 제목

	String requestcondition_l;	// ('N', 'T', 'Y', 'F')), 의뢰상황 아직/ 하는중/ 성공/ 실패
    
	String inputdate;	        // 의뢰 날짜
	String enddate;	            // 만료 날짜 지정 
	String workdate;	        // 작업 기간 삭제

	String cash;                // 의뢰 포인트

	String originrfile;	        // 원본 의뢰 파일
	String savedrfile;     	    // 저장 의뢰 파일
	String origintfile;	        // 원본 번역 파일
	String savedtfile;	        // 저장 번역 파일

	String comment_lr;          // 비고란
	String commentpage;	        // 글자수 및 페이지 수
    
	String startlang;	        // 시작 언어
	String endlang;	            // 도착 언어

	String auction;
}
