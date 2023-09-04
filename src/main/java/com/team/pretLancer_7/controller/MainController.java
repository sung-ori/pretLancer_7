package com.team.pretLancer_7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.team.pretLancer_7.domain.Member;
import com.team.pretLancer_7.service.MemberService;

@Controller
public class MainController {
    
	@Autowired
	MemberService service;
	
	// 로그인 후 메인페이지
    @GetMapping({" ", "/"})
    public String mainForm(@AuthenticationPrincipal UserDetails user, Model m) {
        // 마감기한(번역을 수주한 경우), 회원등급, 신뢰도, 보유포인트 불러오는 메서드 필요. 
    	Member member = service.getUser(user.getUsername());
    	m.addAttribute("member", member);
        return "main";
    }
    
    // 로그인 전 페이지
    @GetMapping("public")
    public String pubilcForm() {
        return "public";
    }
     
}