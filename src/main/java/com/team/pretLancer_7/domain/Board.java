package com.team.pretLancer_7.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board {
    int     boardnum;       // 커뮤니티 게시물 고유 번호 시퀀스 사용
    String  memberid;      // 작성자 아이디
    String  boardtitle;     // 커뮤니티 게시물 제목
    String  boardcontent;   // 커뮤니키 게시물 내용
    String  inputdate;       // 작성일 
    int     hits;           // 조회수
    int     recommend;      // 추천수
    int     decommend;      // 반대수
    int     police;         // 신고 수 
}