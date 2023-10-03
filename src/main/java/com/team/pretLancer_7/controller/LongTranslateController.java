package com.team.pretLancer_7.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.team.pretLancer_7.domain.Ability;
import com.team.pretLancer_7.domain.AuctionTranslator;
import com.team.pretLancer_7.domain.Member;
import com.team.pretLancer_7.domain.MyPage;
import com.team.pretLancer_7.domain.Request_L;
import com.team.pretLancer_7.messaging.MessagingService;
import com.team.pretLancer_7.service.LongService;
import com.team.pretLancer_7.service.MemberService;
import com.team.pretLancer_7.service.MypageService;
import com.team.pretLancer_7.utill.PageNavigator;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/long")
public class LongTranslateController {
    
    @Autowired
    LongService service;
    @Autowired
    MessagingService msg;
    @Autowired
    MypageService mService;
    @Autowired
    MemberService Mservice;

    @Value("${user.TL.page}")
	int countPerPage;
	//게시판 목록의 페이지 이동 링크 수
	@Value("${user.TL.group}")
	int pagePerGroup;
    

    @GetMapping("/main")
    public String mainForm(){
        return "translate_long/mainForm";
    }

    @GetMapping("/request")
    public String requestForm(Model model,@AuthenticationPrincipal UserDetails user,String type,
    @RequestParam(name="page",defaultValue="1") int page) {

        String userid = user.getUsername();
        Map<String,String> map = new HashMap();
        map.put("type", type);
        map.put("userid", userid);

        PageNavigator navi = service.getPageNavigatorT(pagePerGroup, countPerPage, page, type, userid);

        List<MyPage> translatorList = service.getTranslatorList(navi,map);


        log.error("돌아오나요? {}", translatorList);
        model.addAttribute("translatorList", translatorList);
        model.addAttribute("navi", navi);

        return "translate_long/translatorList";
    }

    @GetMapping("/translatorProfile")
    public String translatorProfile(Model model, @RequestParam(name="memberid") String memberid, @AuthenticationPrincipal UserDetails user ) {
        String loginId = user.getUsername();
        MyPage translatorProfile = service.getOneMyPage(memberid);
        Ability ab = mService.getAbility(user.getUsername());

        model.addAttribute("ability", ab);
        model.addAttribute("tp", translatorProfile);
        model.addAttribute("loginId", loginId);


        return "/translate_long/translatorProfile";
    }

    @GetMapping("/writeRequest")
    public String request(Model model, @RequestParam(name="memberid") String memberid, @AuthenticationPrincipal UserDetails user) {
        MyPage translatorProfile = service.getOneMyPage(memberid);
        String loginId = user.getUsername();
        
        model.addAttribute("translator", translatorProfile);
        model.addAttribute("loginId", loginId);


        return "/translate_long/writeRequestForm";
    }

    @PostMapping("/writeRequest")
    public String writeRequest(Request_L request_L, MultipartFile uploadFile, @AuthenticationPrincipal UserDetails user){
        // String originFileName = uploadFile.getOriginalFilename();
        log.debug("장문 요청 컨트롤러 {}",request_L);
        // log.debug("오리진 파일 {}", originFileName);
        
        // 캐쉬 마이너스 안되게
        Member member = Mservice.getUser(user.getUsername());
        long number = Long.parseLong(request_L.getCash());
		if (member.getCash() < number) {
			return "errorForm/Nocash";
		}
        
        request_L.setMemberid(user.getUsername());

        service.writeRequest(request_L,uploadFile);

        service.pay(user.getUsername(),request_L.getCash());

        msg.writeLR(request_L);
        return "redirect:/main";
    }

    @GetMapping("/auction")
    public String auctionForm() {
        return "/translate_long/writeAuctionForm";
    }

    @PostMapping("/writeAuction")
    public String writeAuction(Request_L request_L, MultipartFile uploadFile, @AuthenticationPrincipal UserDetails user) {
    	
    	// 캐쉬 마이너스 안되게
        Member member = Mservice.getUser(user.getUsername());
        long number = Long.parseLong(request_L.getCash());
		if (member.getCash() < number) {
			return "errorForm/Nocash";
		}
    	
        request_L.setMemberid(user.getUsername());

        service.writeAuction(request_L,uploadFile);

        return "redirect:/main";
    }

    @GetMapping("/auctionList")
    public String auctionList(Model model,@AuthenticationPrincipal UserDetails user,@RequestParam(name="page",defaultValue="1") int page,String type) {
        log.debug("출발은 하는가?");
        String userid = user.getUsername();
        Map<String,String> map = new HashMap();
        map.put("type",type);
        log.debug("맵에는 들어가는가?")     ;
        PageNavigator navi = service.getPageNavigatorA(pagePerGroup, countPerPage, page, type);
        log.debug("갯수 세고 돌아오는가? {}",navi);
        List<Request_L> auctionList =  service.getAuctionList(navi,map);
        log.debug("옥션 갖고 돌아오는가? {}",auctionList); 
        model.addAttribute("auctionList", auctionList);
        model.addAttribute("navi", navi);
        return "/translate_long/auctionListForm";
    }
    
    @GetMapping("readAuctionInfo")
    public String readAuctionInfo(Model model, @RequestParam(name="requestnum_l") int requestnum_l) {
        Request_L rql = service.readAuctionInfo(requestnum_l);
        int auctionNum  = service.getAuctionNumber(rql.getRequestnum_l());

        model.addAttribute("info", rql);
        model.addAttribute("auctionNum", auctionNum);

        return "/translate_long/auctionInfo";
    }

    @GetMapping("readAuctionPrice")
    @ResponseBody
    public List<AuctionTranslator> readAuctionPrice(@RequestParam(name="auctionNum") int auctionNum) {
        List<AuctionTranslator> list ;
        list = service.readAuctionPrice(auctionNum);

        return list;
    }

    @GetMapping("bid")
    @ResponseBody
    public void bid(AuctionTranslator at,@AuthenticationPrincipal UserDetails user){
        at.setMemberid(user.getUsername());
        log.debug("컨트롤러 경매 입찰 삽입 {}", at);
        service.setBid(at);
    }

    @GetMapping("bidValidation")
    @ResponseBody
    public String bidValidation(@AuthenticationPrincipal UserDetails user, String auctionNum) {
        Map<String, String> map = new HashMap();
        map.put("auctionnum", auctionNum);
        map.put("memberid", user.getUsername());
        
        return service.bidValidation(map);
    }
// 테이블에 경먀 여부를 적어놓았다. 불러오면 다 불러 진다. 


//====
    @GetMapping("/cashCheck")
    @ResponseBody
    public String cashCheck(@AuthenticationPrincipal UserDetails user,int cash) {
        String userid = user.getUsername();
        String rst = service.cashCheck(userid, cash);

        return rst;
    }

    @GetMapping("successfulBid")
	@ResponseBody
	public void successfulBid(String biderid,String requestnum, String auctionnum) {
		
		Map<String, String> map = new HashMap() ;
		map.put("memberid",biderid);
		map.put("requestnum", requestnum);
		map.put("auctionnum",auctionnum);
		int rst =  service.successfulBid(map);

	}

    @GetMapping("/readRequestInfo")
    public String readRequestInfo(Model model, @RequestParam(name = "requestnum_l") int requestnum_l) {

        Request_L request =  service.readRequestInfo(requestnum_l);
        model.addAttribute("request", request);
        return "/translate_long/requestInfo";
    }

    @GetMapping("/requestResponse")
    @ResponseBody
    public String requestResponse(String requestnum, String message) {
        log.debug("컨트롤러 들어오나 확인");
        Map<String, String> map = new HashMap();
        map.put("requestnum", requestnum);
        map.put("message", message);
        return  service.resoponseToRequest(map);
    }
    // 번역가가 현재 변역중인지 확인.
    @GetMapping("/checkTranslateNow")
    @ResponseBody
    public String checkTranslateNow (String memberid) {
        String rst = "false";

        Request_L rql = service.checkTranslateNow(memberid);

        if(rql == null) {
            rst = "true";
        }
        log.info("번역 확인 {}",rst);
        return rst;
    }

    @GetMapping("/readAccessRequestInfo")
    public String readAccessRequestInfo(Model model, @RequestParam(name = "requestnum_l") int requestnum_l) {

        Request_L request =  service.readRequestInfo(requestnum_l);
        model.addAttribute("request", request);
        return "/translate_long/accessInfo";
    }

    @PostMapping("/uploadTest")
    @ResponseBody
    public void uploadTest(@RequestParam("uploadfile") MultipartFile uploadfile,@RequestParam("requestnum_l") int requestnum_l) {
        log.info("알려줘! {},{}", uploadfile.getOriginalFilename(),requestnum_l);
         service.uploadResult(uploadfile,requestnum_l);
         msg.writeLC(requestnum_l);
    }

    @GetMapping("/readResult")
    public String readResult(@RequestParam("requestnum_l") int requestnum_l, Model model) {

        Request_L rql =  service.readRequestInfo(requestnum_l);
        model.addAttribute("result", rql);

        return "/translate_long/resultForm";
    }
    
    @GetMapping("/success")
    @ResponseBody
    public void success(@RequestParam("requestnum_l") int requestnum_l) {
        log.debug("되냐? : {}",""+requestnum_l);
         service.success(requestnum_l);
         msg.writeLS(requestnum_l);
        
    }
   @GetMapping("cancel")
   @ResponseBody
   public String cancel(@RequestParam("requestnum_l") int requestnum_l) {

    String rst = service.requestCancel(requestnum_l);
    return rst;
   }
}
