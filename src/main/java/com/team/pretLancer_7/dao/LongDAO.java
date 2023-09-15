package com.team.pretLancer_7.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.team.pretLancer_7.domain.AuctionTranslator;
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

    // 하나의 요청를 삽입
    public int insertOneRequstAuction (Request_L request_L);
    // 이러긴 싫지만 지금 넣은 키 값을 리턴
    public int selectMaxRequestnum();
    // 경매 테이블에 삽입
    public int insertAuction(int requestnum);
    // 요청 중 경매 테이블에 들어있는 것들만 출력
    public List<Request_L> selectAuctionList() ;

    // 하나의 요청을 읽는다. 나는 경매 정보 의 상세 정보에 쓸 것이다.
    public Request_L selectOneRequest_L (int requestnum_l);
    // 리퀘스트넘버로 옥션넘버를 구한다 
    public int selectAuctionNum(int requestnum_l);
    // 입찰가 등 정보를 맵에 불러올 거야 .
    public List<AuctionTranslator> selectAuctionInfo(int auctionNum );
    // 경매 지원자 테이블에 경매번호, 지원자, 입찰가격을 집어넣는다. 
    public int insertAuctionTranslator(AuctionTranslator at);
    //
    public int countAuctoinBid(Map<String, String> map);
}
