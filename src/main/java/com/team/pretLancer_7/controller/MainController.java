package com.team.pretLancer_7.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team.pretLancer_7.domain.Board;
import com.team.pretLancer_7.domain.Member;
import com.team.pretLancer_7.domain.Message;
import com.team.pretLancer_7.domain.QnA;
import com.team.pretLancer_7.domain.Request_L;
import com.team.pretLancer_7.messaging.MessagingService;
import com.team.pretLancer_7.service.CommunityService;
import com.team.pretLancer_7.service.EvaluationService;
import com.team.pretLancer_7.service.LongService;
import com.team.pretLancer_7.service.MemberService;
import com.team.pretLancer_7.service.RequestService;
import com.team.pretLancer_7.service.TranslatedService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MainController {
    
	@Autowired
	MemberService service;
	
	@Autowired
	TranslatedService Tservice;
	
	@Autowired
	RequestService Rservice;
	
	@Autowired
	CommunityService Cservice;
	
    @Autowired
    MessagingService Mservice;
	
    @Autowired
    LongService Lservice;

	// 로그인 전 메인페이지
    @GetMapping({" ", "/"})
    public String mainForm() {
        // 마감기한(번역을 수주한 경우), 회원등급, 신뢰도, 보유포인트 불러오는 메서드 필요. 
        return "public";
    }
  
    
    // 로그인 후 페이지
    @GetMapping("main")
    public String pubilcForm(@AuthenticationPrincipal UserDetails user, Model m) {
    	
    	Member member = service.getUser(user.getUsername());
    	m.addAttribute("member", member);
    	
    	// 홈페이지 보여줄 값
    	List<Board> today = Cservice.todayPopular();
    	
    	if (today != null) {
    		m.addAttribute("community", today);
    	}
    		
    	int Rcount = Rservice.RequestCount();
    	int TcountS = Rservice.TranslatedCountS();
    	int TcountM = Rservice.TranslatedCountM();
    	int Ecount = Rservice.EvaluationCount();
    	
        m.addAttribute("Rcount", Rcount);
        m.addAttribute("TcountS", TcountS);
        m.addAttribute("TcountM", TcountM);
        m.addAttribute("Ecount", Ecount);
        
    	return "main3";
    }
 
    //문의 페이지
    @GetMapping("qna")
    public String qnaForm() {
    	return "qna";
    }
 
    //문의 페이지
    @PostMapping("qna")
    public String qnaForm(QnA q) {
    	// int n = service.insertQnA(q);
    	
    	return "redirect:/qna";
    }
 
    
    //사이드바,Nav바 로드
    @GetMapping("snBar")
    public String snBar(Model model, @AuthenticationPrincipal UserDetails user){
        String userName = user.getUsername();
        Member loginUser = service.getUser(userName);
        
        Request_L rql = Lservice.checkTranslateNow(userName);

        model.addAttribute("user", loginUser);

        // if(rql == null) {
        //     rql.setEnddate("번역을 좀 하세요 이 게으름뱅이야.");
        // }
        model.addAttribute("rql",rql);

    	return "fragments/snBar";
    }

    @GetMapping("/message")
    @ResponseBody
    public List<Message> messageBox(@AuthenticationPrincipal UserDetails user) {
        
        log.debug("메세지 컨트롤러는 들어오냐? {}");
        List<Message> msg = Mservice.getMyMessages(user.getUsername());
        return msg;
    }

    @GetMapping("/messagecnt")
    @ResponseBody
    public int messagecnt(@AuthenticationPrincipal UserDetails user) {
        
        int cnt = Mservice.getMyMessages(user.getPassword()).size();
        return cnt ;
    }

    @GetMapping("/messageCk")
    @ResponseBody
    public void messageCk(@AuthenticationPrincipal UserDetails user) {
        Mservice.checked(user.getUsername());
    }

    @GetMapping("/messageCl")
    @ResponseBody
    public void messageCl(int messagenum) {
        Mservice.clicked(messagenum);
    }
}