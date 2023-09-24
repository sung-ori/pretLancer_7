package com.team.pretLancer_7.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.team.pretLancer_7.domain.AuctionTranslator;
import com.team.pretLancer_7.domain.MyPage;
import com.team.pretLancer_7.domain.Request_L;

public interface LongService {
    public List<MyPage> getTranslatorList(String userId);

    public MyPage getOneMyPage(String memberid);

    public int writeRequest(Request_L request_L,MultipartFile uploadFile);

    public int writeAuction(Request_L request_L,MultipartFile uploadFile);

    public List<Request_L>  getAuctionList();
    
    public Request_L readAuctionInfo(int requestnum_l);

    public int getAuctionNumber(int requestnum);

    public List<AuctionTranslator> readAuctionPrice(int auctionNum);
    // 입찰 정보 삽입
    public int setBid(AuctionTranslator at);
    // 중복 입찰 확인
    public String bidValidation(Map<String, String> map);
    // 내가 신청한 경매 리스트 
    // TODO: 이거 그냥 요청이랑 경매 다 출력하고 페이지에서 동작 바꾸는게 페이지가 줄어들 듯, 
    public List<Request_L> myAuctionList (String userid);
    // 낙찰
    public int successfulBid(Map<String, String> map);
    // 유저에게 온 요청들을 출력
    public List<Request_L> getRequestToMe (String userid);

    public Request_L readRequestInfo(int requestnum_l);
    
    public String resoponseToRequest(Map<String, String> map);

    public Request_L checkTranslateNow(String userid);
    
}
