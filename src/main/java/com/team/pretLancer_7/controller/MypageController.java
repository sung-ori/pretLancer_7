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
import org.springframework.web.multipart.MultipartFile;

import com.team.pretLancer_7.domain.Member;
import com.team.pretLancer_7.domain.MyPage;
import com.team.pretLancer_7.domain.Request_L;
import com.team.pretLancer_7.service.LongService;
import com.team.pretLancer_7.service.MypageService;
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
	LongService Lservice;

    @GetMapping("main")
    public String myPageForm(Model m, @AuthenticationPrincipal UserDetails user) {
        
        MyPage mp =  service.getMyPage(user.getUsername());
        m.addAttribute("mypage", mp);
        
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
    @PostMapping("MpUpdate")
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
    	
    	return "redirect:/my_page/main";
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


	 @GetMapping("/myRequestList")
    public String myAuctionList(@AuthenticationPrincipal UserDetails user, Model model) {
        List<Request_L> myAuctionList =  Lservice.myAuctionList(user.getUsername());
        List<Request_L> myRequestList =  Lservice.myRquestList(user.getUsername());
        
        model.addAttribute("myRequestList", myRequestList);
        model.addAttribute("myAuctionList", myAuctionList);
        log.debug("컨트롤러에 오는 나의 옥션 리스트 {}", myRequestList);

        return "/mypageform/myRequestList";
    }

	
    @GetMapping("/requestToMe")
    public String requestToMe(Model model, @AuthenticationPrincipal UserDetails user) {
		List<Request_L> list =  Lservice.getRequestToMe(user.getUsername());
		
        model.addAttribute("list", list);
		
        return "/mypageform/requestToMe";
		
    }
    
	
	
}
