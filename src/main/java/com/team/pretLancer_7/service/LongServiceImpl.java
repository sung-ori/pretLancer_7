package com.team.pretLancer_7.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.team.pretLancer_7.dao.LongDAO;
import com.team.pretLancer_7.dao.MemberDAO;
import com.team.pretLancer_7.domain.AuctionTranslator;
import com.team.pretLancer_7.domain.MyPage;
import com.team.pretLancer_7.domain.Request_L;
import com.team.pretLancer_7.utill.FileService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class LongServiceImpl implements LongService{

    @Value("/Users/sung_ori/pretLancer_7/pretLancer_7/src/main/resources/static/img")
	String uploadPath;

    @Autowired
    LongDAO dao ;

    @Autowired
    MemberDAO Mdao;

    @Override
    public List<MyPage> getTranslatorList(String userid) {
        
        List<MyPage> translatorList = new ArrayList();
        translatorList =  dao.selectAdTranslator();
        
        int idx = 0;

        for(MyPage translatorProfile : translatorList ) {
            if (translatorProfile.getMemberid().equals(userid)) {
                translatorList.remove(idx);
                break;
            }
            idx++;
            
        }

        log.error("서비스는 돌아오나? {}", translatorList);
        return translatorList;
    }

    @Override
    public MyPage getOneMyPage(String memberid) {
        return dao.selectOneMyPage(memberid);
    }

    @Override
    public int writeRequest(Request_L request_L, MultipartFile uploadFile) {
        FileService fileService = new FileService();
        log.debug("장문 요청 서비스 {}",request_L);
        log.debug("업로드 경로", uploadPath);
        
        String originfile =""; 
        String savedfile = "";
        try{
            originfile = uploadFile.getOriginalFilename();
            savedfile = fileService.saveFile(uploadFile,uploadPath);
            
        }
        catch (NullPointerException e) {
        log.debug("오리진", originfile);
        log.debug("세이브", savedfile);
        }
        
        request_L.setOriginrfile(originfile);
        request_L.setSavedrfile(savedfile);

        return dao.insertOneRequst(request_L);

    }

    @Override
    @Transactional
    public int writeAuction(Request_L request_L, MultipartFile uploadFile) {
        
        FileService fileService = new FileService();
        log.debug("장문 요청 서비스 {}",request_L);
        log.debug("업로드 경로", uploadPath);
        
        String originfile =""; 
        String savedfile = "";
        try{
            originfile = uploadFile.getOriginalFilename();
            savedfile = fileService.saveFile(uploadFile,uploadPath);
            
        }
        catch (NullPointerException e) {
        log.debug("오리진", originfile);
        log.debug("세이브", savedfile);
        }
        
        request_L.setOriginrfile(originfile);
        request_L.setSavedrfile(savedfile);

        
        dao.insertOneRequst(request_L);

        int requestnum_l = dao.selectMaxRequestnum();


        log.debug("돌아와요 부산항에 {}",requestnum_l);
        

        return dao.insertAuction(requestnum_l);

    }
    //  경매 리스트를 불러온다
    @Override
    public List<Request_L> getAuctionList() {
        
        List<Request_L> auctionList = dao.selectAuctionList();

        int idx = 0;

        for(Request_L rql : auctionList) {
            if(!rql.getRequestcondition_l().equals("N")) {
                auctionList.remove(idx);
            }   
            idx++;
        }
        return auctionList;
    }
    // 
    @Override
    public Request_L readAuctionInfo(int requestnum_l) {
        Request_L rql  = dao.selectOneRequest_L(requestnum_l);
        return rql;
    }

    @Override
    public List<AuctionTranslator> readAuctionPrice(int auctionNum) {

        List<AuctionTranslator> ATList = dao.selectAuctionInfo(auctionNum);

        return ATList;
    }

    @Override
    public int getAuctionNumber(int requestnum) {

        int auctionNum =  dao.selectAuctionNum(requestnum);
        return auctionNum;
    }

    @Override
    public int setBid(AuctionTranslator at) {

        at.setMem_level(Mdao.selectOne(at.getMemberid()).getMem_level());
        
        return dao.insertAuctionTranslator(at);
    }

    @Override
    public String bidValidation(Map<String, String> map) {
        AuctionTranslator result =  dao.selectAuctionBid(map);

        String rst = "false";

        if(result == null) {
            rst = "true";
        }
        return rst;
    }

    @Override
    public List<Request_L> myAuctionList(String userid) {
        List<Request_L> list = dao.selectAuctionList();
        ArrayList<Request_L> myAuctionList = new ArrayList();;

        int idx = 0;
        for(Request_L auction :list) {
            if (auction.getMemberid().equals(userid)) {
                myAuctionList.add(idx,auction);
                idx++;
            }
        }
        
        return myAuctionList;
    }

    @Override
    public int successfulBid(Map<String, String> map) {

        String cash = dao.selectAuctionBid(map).getTranslatervalue();
        map.put("cash", cash);
        return dao.updateRequestAuction(map);
    }

    @Override
    public List<Request_L> getRequestToMe(String userid) {
        return dao.selectRequestToMe(userid);
    }

    @Override
    public Request_L readRequestInfo(int requestnum_l) {

        return dao.selectOneRequest_L(requestnum_l);
    }

    @Override
    public String resoponseToRequest(Map<String, String> map) {
        
        String result = "";

        if(map.get("message").equals("accept")) {
            dao.updateRequestResponse(map);
            result = "accept";
        
        }

        if(map.get("message").equals(("refuse"))) {
            dao.updateRequestResponse(map);
                result = "refuse";
        }
        return result;
    }
    
    
}
