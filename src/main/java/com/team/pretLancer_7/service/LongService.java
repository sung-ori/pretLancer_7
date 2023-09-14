package com.team.pretLancer_7.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.team.pretLancer_7.domain.AuctionTranslator;
import com.team.pretLancer_7.domain.MyPage;
import com.team.pretLancer_7.domain.Request_L;

public interface LongService {
    public List<MyPage> getTranslatorList();

    public MyPage getOneMyPage(String memberid);

    public int writeRequest(Request_L request_L,MultipartFile uploadFile);

    public int writeAuction(Request_L request_L,MultipartFile uploadFile);

    public List<Request_L>  getAuctionList();
    
    public Request_L readAuctionInfo(int requestnum_l);

    public List<AuctionTranslator> readAuctionPrice(int requestnum_l);
}
