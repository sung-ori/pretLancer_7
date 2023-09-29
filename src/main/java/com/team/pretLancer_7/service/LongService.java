package com.team.pretLancer_7.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.team.pretLancer_7.domain.AuctionTranslator;
import com.team.pretLancer_7.domain.MyPage;
import com.team.pretLancer_7.domain.Request_L;
import com.team.pretLancer_7.utill.PageNavigator;

public interface LongService {
    public List<MyPage> getTranslatorList(PageNavigator navi, Map<String,String> map);

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
    

    public List<Request_L> myRquestList (String uesrid);
    // 낙찰
    public int successfulBid(Map<String, String> map);
    // 유저에게 온 요청들을 출력
    public List<Request_L> getRequestToMe (String userid);

    public Request_L readRequestInfo(int requestnum_l);
    
    public String resoponseToRequest(Map<String, String> map);

    public Request_L checkTranslateNow(String userid);

    public int uploadResult (MultipartFile uploadFile, int requestnum);

    public void success(int requestnum_l);

    public String cashCheck(String userid, int cash);

    public void pay(String userid ,String cash);

    public void getmoney(String userid, String cash);

    public PageNavigator getPageNavigatorT(int pagePerGroup, int countPerPage, int page, String type, String userid);

    public PageNavigator getPageNavigatorA(int pagePerGroup, int countPerPage, int page, String type, String userid);
}
