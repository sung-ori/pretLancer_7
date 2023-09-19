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

import com.team.pretLancer_7.domain.Member;
import com.team.pretLancer_7.domain.Message;
import com.team.pretLancer_7.domain.QnA;
import com.team.pretLancer_7.domain.Request_L;
import com.team.pretLancer_7.messaging.MessagingService;
import com.team.pretLancer_7.service.LongService;
import com.team.pretLancer_7.service.MemberService;

@Controller
public class MainController {
    
	@Autowired
	MemberService service;

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
        model.addAttribute("rql",rql);
        // if(rql != null) {
        // }

    	return "fragments/snBar";
    }

    @GetMapping("/message")
    @ResponseBody
    public List<Message> messageBox(@AuthenticationPrincipal UserDetails user) {
        
        List<Message> msg = Mservice.getMyMessages(user.getUsername());
        return msg;
    }
}