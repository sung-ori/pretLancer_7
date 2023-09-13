package com.team.pretLancer_7.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.team.pretLancer_7.domain.MyPage;
import com.team.pretLancer_7.domain.Request_L;

@Mapper
public interface LongDAO {
    // 광고 붙인 번역가 회원을 불러옴
    public List<MyPage> selectAdTranslator();
    // 선택한 번역가의 프로필을 불러옴
    public MyPage selectOneMyPage(String memberid);
    // 하나의 요청을 삽입
    public int insertOneRequst(Request_L request_L);

    // 하나의 요청를 삽입하고 경매 테이블에 넣기 위해 그 키 값을 리턴
    
    public Request_L insertOneRequstAuction (Request_L request_L);
    // 경매 테이블에 삽입
    public int insertAuction(int requestnum);
}
