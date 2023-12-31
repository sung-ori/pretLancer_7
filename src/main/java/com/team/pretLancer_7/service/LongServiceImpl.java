package com.team.pretLancer_7.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
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
import com.team.pretLancer_7.messaging.MessagingService;
import com.team.pretLancer_7.utill.FileService;
import com.team.pretLancer_7.utill.PageNavigator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j

public class LongServiceImpl implements LongService{

    @Value("C:/requestFile")
	String uploadPathR;

    @Value("C:/transFile")
	String uploadPathT;

    @Autowired
    LongDAO dao ;

    @Autowired
    MemberDAO Mdao;

    @Autowired
    MessagingService msg;

    @Override
    public List<MyPage> getTranslatorList(PageNavigator navi, Map<String,String> map) {
        
        List<MyPage> translatorList = new ArrayList();
        RowBounds rb = new RowBounds(navi.getStartRecord(),navi.getCountPerPage());

        translatorList =  dao.selectAdTranslator(map,rb);
        
        // int idx = 0;
        // //  현재 번역 중인지 확인하고 번역 중이면 출력 안해준다.
        // while(true) {
        //     int max = translatorList.size();

        //     String a = translatorList.get(idx).getMemberid();
            
        //     Request_L rql = dao.selectTranslateNow(a);

        //     if (a.equals(userid) || rql != null) {
        //         translatorList.remove(idx);
        //         idx = 0;
        //         continue;
        //     }
        //     idx++;

        //     if(idx == max) {
        //         break;
        //     }
        // }

        // log.error("서비스는 돌아오나? {}", translatorList);
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
        log.debug("업로드 경로", uploadPathR);
        
        String originfile =""; 
        String savedfile = "";
        try{
            originfile = uploadFile.getOriginalFilename();
            savedfile = fileService.saveFile(uploadFile,uploadPathR);
            
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
        log.debug("업로드 경로", uploadPathR);
        
        String originfile =""; 
        String savedfile = "";
        try{
            originfile = uploadFile.getOriginalFilename();
            savedfile = fileService.saveFile(uploadFile,uploadPathR);
            
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
    public List<Request_L> getAuctionList(PageNavigator navi, Map<String, String> map) {
        
        RowBounds rb = new RowBounds(navi.getStartRecord(),navi.getCountPerPage());
        ArrayList<Request_L> auctionList = dao.selectAuctionList(map,rb);

        

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

    

    public List<Request_L> myRquestList(String userid){
        return dao.selectRequestList(userid);
    }

    @Override
    public int successfulBid(Map<String, String> map) {

        String cash = dao.selectAuctionBid(map).getTranslatervalue();
        map.put("cash", cash);
        
        // 낙찰금액 / 낙찰 회원 / 요청 번호 / 옥션 번호
        int a = dao.updateRequestAuction(map);

        pay(map.get("memberid"),cash);

        msg.writeLB(map);
        return a;
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

            msg.writeLA(map);

            result = "accept";
        
        }

        if(map.get("message").equals(("refuse"))) {
            dao.updateRequestResponse(map);
            msg.writeLF(map);
                result = "refuse";
        }
        return result;
    }

    @Override
    public Request_L checkTranslateNow(String userid) {
        return dao.selectTranslateNow(userid);
    }
    
    @Override
    @Transactional
    public int uploadResult(MultipartFile uploadFile, int requestnum) {
        
        Map<String, String> map = new HashMap();
        map.put("message", "uploadResult");
        map.put("requestnum",""+requestnum);

        FileService fileService = new FileService();
        log.debug("장문 요청 서비스 {}",requestnum);
        log.debug("업로드 경로", uploadPathR);
        
        String originfile =""; 
        String savedfile = "";
        try{
            originfile = uploadFile.getOriginalFilename();
            savedfile = fileService.saveFile(uploadFile,uploadPathT);
            
        }
        catch (NullPointerException e) {
        log.debug("오리진", originfile);
        log.debug("세이브", savedfile);
        }
        
        map.put("originfile", originfile);
        map.put("savedfile", savedfile);
        
        

        return dao.updateRequestResponse(map);

    }

    @Override
    public void success(int requestnum_l) {
        Map<String,String> map = new HashMap();

        map.put("message", "success");
        map.put("requestnum", ""+requestnum_l);
        Request_L rql = dao.selectOneRequest_L(requestnum_l);

        getmoney(rql.getMemberid2(), rql.getCash());

        dao.updateRequestResponse(map);
    }

    @Override
    public String cashCheck(String userid, int cash) {
        int usercash = Mdao.selectOne(userid).getCash();
        String rst = "NO";

        if(usercash > cash) {
            rst = "OK";
        }
        
        return rst;
    }

    @Override
    public void pay(String userid, String cash) {
        Map<String,String> map = new HashMap();
        // map.put("message","pay");
        Integer a = Integer.parseInt(cash);
        map.put("userid", userid);
        map.put("cash", cash);
        log.info("오냐? {}", map);
        dao.updateCashPay(map);
    }

    @Override
    public void getmoney(String userid, String cash) {
        Map<String, String> map = new HashMap();
        // map.put("message","getmoney");
        map.put("userid", userid);
        map.put("cash", cash);
        log.info("오냐? {}", map);

        dao.updateCashGet(map);
    }

    @Override
    public PageNavigator getPageNavigatorT(int pagePerGroup, int countPerPage, int page, String type, String userid) {
        
        HashMap<String, String> map = new HashMap<>();
		map.put("type", type);
		map.put("userid", userid);
        
        int total = dao.countT(map);

		PageNavigator navi = new PageNavigator(pagePerGroup, countPerPage, page,total);
		
		return navi;
    }

    @Override
    public PageNavigator getPageNavigatorA(int pagePerGroup, int countPerPage, int page, String type) {
        
        Map<String, String> map = new HashMap<>();

        map.put("type", type);
        
		
		log.debug("타입 확인좀 해 보자. {}",type);
        int total = 0;
        //  이 지점에서 오류 발생.
        try{

            total = dao.countA(map);
        }catch(Exception e){
            
            log.error("에러 내용 : {} ", e.getStackTrace());
        
        }
        log.debug("갯수는 세는가? {}",total);

		PageNavigator navi = new PageNavigator(pagePerGroup, countPerPage, page,total);
		log.debug("나비스 {}",map);
		return navi;
    }

    @Override
    public String requestCancel(int requestnum_l) {
        
        String rst = "NO";
        int a = dao.deleteRequest(requestnum_l);
        
        if (a != 0) {
            rst = "OK";
        }
        return rst;
    }
}
