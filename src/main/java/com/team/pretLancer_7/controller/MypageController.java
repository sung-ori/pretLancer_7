package com.team.pretLancer_7.controller;

import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.team.pretLancer_7.domain.Ability;
import com.team.pretLancer_7.domain.Member;
import com.team.pretLancer_7.domain.MyPage;
import com.team.pretLancer_7.domain.Request_L;
import com.team.pretLancer_7.domain.Request_M;
import com.team.pretLancer_7.domain.Request_S;
import com.team.pretLancer_7.domain.Translated_M;
import com.team.pretLancer_7.domain.Translated_S;
import com.team.pretLancer_7.service.LongService;
import com.team.pretLancer_7.service.MemberService;
import com.team.pretLancer_7.service.MypageService;
import com.team.pretLancer_7.service.RequestService;
import com.team.pretLancer_7.service.TranslatedService;
import com.team.pretLancer_7.utill.FileService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("my_page")
public class MypageController {
	
	@Value("${spring.servlet.multipart.location}")
	String uploadPath;

    @Autowired
    MypageService service;

    @Autowired
    MemberService mservice;

	@Autowired
	LongService Lservice;
	
	@Autowired
	RequestService Rservice;

	@Autowired
	TranslatedService Tservice;
	
	@Autowired
	MypageService Myservice;
	
    @GetMapping("main")
    public String myPageForm(Model m, @AuthenticationPrincipal UserDetails user) {
        
        MyPage mp =  service.getMyPage(user.getUsername());
        m.addAttribute("mypage", mp);
        
        // 내 번역율 (%)
        int Pper = service.getPper(user.getUsername());
        int Sper = service.getSper(user.getUsername());
        int Mper = service.getMper(user.getUsername());
        int Eper = service.getEper(user.getUsername());
        Ability ab = service.getAbility(user.getUsername());
        
        m.addAttribute("Pper", Pper);
        m.addAttribute("Sper", Sper);
        m.addAttribute("Mper", Mper);
        m.addAttribute("Eper", Eper);
        m.addAttribute("ability", ab);
        
        return "/myPageForm/MyPage";
    }
    
    
    // 개인정보 수정
    @PostMapping("update")
    public String update(@AuthenticationPrincipal UserDetails user, Member m) {
    	m.setMemberid(user.getUsername());
    	int cnt = service.updateMember(m);
    	return "redirect:/my_page/main";
    }
    
    
    // 마이프로필 페이지
    @GetMapping("MyProfile")
    public String MyProfile() {
    	
    	return "/myPageView/MyProfile";
    }
    
   
    // 마이프로필 수정
    /*@PostMapping("MpUpdate")
    public String update(@AuthenticationPrincipal UserDetails user, MyPage Mp, MultipartFile upload) {
    	
    	MyPage MyPage = service.getMyPage(user.getUsername());
    	Mp.setProfilecomment("버그 확인 중");
    	log.error("MyPage에 들어가 있는 것 {}", MyPage);
    	log.error("upload에 들어가 있는 것 {}", upload);
    	
    	// 업로드된 사진 처리
    	if(MyPage.getOriginphoto() != null 
    			&& !MyPage.getOriginphoto().isEmpty()
    			&& !MyPage.getOriginphoto().equals("basic.jpg")
    			&& upload != null && !upload.isEmpty()) {
    	// 삭제 처리
    	FileService.deleteFile(uploadPath + "/" + MyPage.getSavedphoto());
    	}
    			
    	// 프로필 사진 저장
    	if (upload != null && !upload.isEmpty()) {
    		String savedfile = FileService.saveFile(upload, uploadPath);
    		Mp.setOriginphoto(upload.getOriginalFilename());
    		Mp.setSavedphoto(savedfile);
    	}
    	
    	// 수정된 내용 저장
    	log.error("Mp에 들어가 있는 것 {}", Mp);
    	int cnt = service.updateProfile(Mp);
    	
    	return "redirect:/";
    }*/
    
    @ResponseBody
    @PostMapping("MpUpdate")
    public void update(@AuthenticationPrincipal UserDetails user, MyPage Mp, MultipartFile upload) {
    	
    	MyPage MyPage = service.getMyPage(user.getUsername());
    	Mp.setProfilecomment("버그 확인 중");
    	log.error("MyPage에 들어가 있는 것 {}", MyPage);
    	log.error("upload에 들어가 있는 것 {}", upload);
    	
    	
    	//업로드된 사진 처리
    	if(MyPage.getOriginphoto() != null 
    			&& !MyPage.getOriginphoto().isEmpty()
    			&& !MyPage.getOriginphoto().equals("basic.jpg")
    			&& upload != null && !upload.isEmpty()) {
    	// 삭제 처리
    	if (!MyPage.getSavedphoto().equals("null.jpg"))
    		FileService.deleteFile(uploadPath + "/" + MyPage.getSavedphoto());
    	}
    	
    			
    	// 프로필 사진 저장
    	if (upload != null && !upload.isEmpty()) {
    		String savedfile = FileService.saveFile(upload, uploadPath);
    		Mp.setOriginphoto(upload.getOriginalFilename());
    		Mp.setSavedphoto(savedfile);
    	}
    	
    	// 수정된 내용 저장
    	log.error("Mp에 들어가 있는 것 {}", Mp);
    	int cnt = service.updateProfile(Mp);


    }
    
    // 프로필 가져오기 + 처음 mapper에 입력할 때 default 값은 basic.jpg로 넣어서 기본 프로필 사진을 가져오게 한다.
    // <p><img th:if="${mypage.originphoto}" th:src="@{/my_page/getPhoto(memberid=${mypage.memberid})}"></p> 사진 불러오는 HTML
    @GetMapping("getPhoto")
	public void getProfilePhoto(String memberid
			, HttpServletRequest request
			, HttpServletResponse response) {
		
		// 첨부사진명 확인
		MyPage MyPage = service.getMyPage(memberid);
		
		// 파일의 경로를 이용해서 FileInputStream 객체를 생성
		String fullPath = uploadPath + "/" + MyPage.getSavedphoto();
		
		// response를 통해 파일 전송
		try {
			response.setHeader("Content-Disposition"
					, " attachment;filename=" + URLEncoder.encode(MyPage.getOriginphoto(), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		try {
			FileInputStream in = new FileInputStream(fullPath);
			ServletOutputStream out = response.getOutputStream();
			FileCopyUtils.copy(in, out);
			in.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
	// =========================


	 @GetMapping("/myRequestList_S")
    public String myAuctionList_S(@AuthenticationPrincipal UserDetails user, Model model) {
        
        List<Request_S> myRequestList =  service.myRquestList_S(user.getUsername());
        
        model.addAttribute("myRequestList", myRequestList);

        return "/mypageform/myRequestList_S";
    }

	 @GetMapping("/myRequestList_M")
	    public String myAuctionList_M(@AuthenticationPrincipal UserDetails user, Model model) {
	        
	        List<Request_M> myRequestList =  service.myRquestList_M(user.getUsername());
	        
	        model.addAttribute("myRequestList", myRequestList);

	        return "/mypageform/myRequestList_M";
	    }

	 @GetMapping("/myRequestList_L")
	    public String myAuctionList_L(@AuthenticationPrincipal UserDetails user, Model model) {
	        
	        List<Request_L> myRequestList =  Lservice.myRquestList(user.getUsername());
	        
	        model.addAttribute("myRequestList", myRequestList);

	        return "/mypageform/myRequestList_L";
	    }

	 @GetMapping("/requestToMe_S")
	    public String requestToMe_S(Model model, @AuthenticationPrincipal UserDetails user) {
			List<Request_S> list =  Rservice.getRequestS(user.getUsername());
			
	        model.addAttribute("list", list);
			
	        return "/mypageform/requestToMe_S";
			
	    }
	 
	    @GetMapping("/requestToMe_M")
	    public String requestToMe_M(Model model, @AuthenticationPrincipal UserDetails user) {
			List<Request_M> list =  Rservice.getRequestM(user.getUsername());
			
	        model.addAttribute("list", list);
			
	        return "/mypageform/requestToMe_M";
			
	    }
	
    @GetMapping("/requestToMe_L")
    public String requestToMe(Model model, @AuthenticationPrincipal UserDetails user) {
		List<Request_L> list =  Lservice.getRequestToMe(user.getUsername());
		
        model.addAttribute("list", list);
		
        return "/mypageform/requestToMe_L";
		
    }
    
    // 내 단문 의뢰 목록
    @GetMapping("/myRequestListS")
    public String myRequestListS() {
    	
    	return "/mypageform/myReqeustListS";
    }
    
    // 내 중문 의뢰 목록
    @GetMapping("/myRequestListM")
    public String myRequestListM() {
    	
    	return "/mypageform/myReqeustListM";
    }
    
    // 내 단문 번역 목록
    @GetMapping("/myTranslatedS")
    public String myTranslatedS() {
    	
    	return "/mypageform/myTranslatedS";
    }
    
    // 내 중문 번역 목록
    @GetMapping("/myTranslatedM")
    public String myTranslatedM() {
    	
    	return "/mypageform/myTranslatedM";
    }
    
    
    // ============================
    
    @GetMapping("changeNick")
    public String changeNick(@AuthenticationPrincipal UserDetails user, Model model, Member member) {
    	member = mservice.getUser(user.getUsername());
    	model.addAttribute("member", member);
    	return "mypageform/changeNick";
    }
	
    @ResponseBody
    @PostMapping("changeNick")
    public void changeNick(String id, String nick) {
    	Member member = new Member();
		
    	member.setMemberid(id);
    	member.setMembernick(nick);
    	int point = service.checkPoint(member);

    	if (point < 300)
    		return;
    	else
    		point = point - 300;
    	
    	member.setPoint(point);
    	service.changeNick(member);
    	
    }
    
    @GetMapping("changePhoto")
    public String changePhoto() {
    	return "mypageform/changePhoto";
    }

    @GetMapping("/readResult_S")
    public String readResult_S(@RequestParam("requestnum_s") int requestnum_s, Model model) {

        Request_S rql =  service.readRequestInfo_S(requestnum_s);
        Translated_S ts = service.getTS(requestnum_s);
        
        model.addAttribute("request", rql);
        model.addAttribute("translated", ts);

        return "/mypageform/resultForm_S";
    }

    @GetMapping("/readResult_M")
    public String readResult(@RequestParam("requestnum_m") int requestnum_m, Model model) {

        Request_M rql =  service.readRequestInfo_M(requestnum_m);
        Translated_M tm = service.getTM(requestnum_m);

        model.addAttribute("request", rql);
        model.addAttribute("translated", tm);

        return "/mypageform/resultForm_M";
    }
    
    @GetMapping("/readResultRequest_S")
    public String readResultRequest_S(@RequestParam("requestnum_s") int requestnum_s, Model model) {

        Request_S rql =  service.readRequestInfo_S(requestnum_s);
        Translated_S ts = service.getTS(requestnum_s);
        
        model.addAttribute("request", rql);
        model.addAttribute("translated", ts);

        return "/mypageform/resultRequestForm_S";
    }

    @GetMapping("/readResultRequest_M")
    public String readResultRequest_M(@RequestParam("requestnum_m") int requestnum_m, Model model) {

        Request_M rql =  service.readRequestInfo_M(requestnum_m);
        Translated_M tm = service.getTM(requestnum_m);

        model.addAttribute("request", rql);
        model.addAttribute("translated", tm);

        return "/mypageform/resultRequestForm_M";
    }
    
    
    @GetMapping("/changeProfile")
    public String changeProfile(Model model, @RequestParam(name="memberid") String memberid, @AuthenticationPrincipal UserDetails user) {
    	 String loginId = user.getUsername();
         MyPage translatorProfile = Lservice.getOneMyPage(memberid);
         Ability ab = Myservice.getAbility(user.getUsername());

         model.addAttribute("ability", ab);
         model.addAttribute("tp", translatorProfile);
         model.addAttribute("loginId", loginId);
    	return "mypageform/changeProfile";
    }
    
    @PostMapping("/changeProfile")
    public String changeProfile(@RequestParam(name="memberid") String memberid, @AuthenticationPrincipal UserDetails user, MyPage mp) {
    	service.changeProfile(mp);
    	return "redirect:/my_page/changeProfile?memberid=" + user.getUsername();
    }
   

    /*
    @GetMapping("/readRequestInfo_S")
    public String readRequestInfo_S(Model model, @RequestParam(name = "requestnum_s") int requestnum_s) {

        Request_S request =  service.readRequestInfo_S(requestnum_s);
        model.addAttribute("request", request);
        return "/mypageform/requestInfo_S";
    }

    @GetMapping("/readRequestInfo_M")
    public String readRequestInfo_M(Model model, @RequestParam(name = "requestnum_m") int requestnum_m) {

        Request_S request =  service.readRequestInfo_S(requestnum_m);
        model.addAttribute("request", request);
        return "/mypageform/requestInfo_M";
    }
	*/
    
    
}
