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

    public List<Request_L> myAuctionList (String userid);
    
}
