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
	
	// 로그인 전 메인페이지
    @GetMapping({" ", "/"})
    public String mainForm() {
       
        return "public";
    }
    
    // 로그인 후 페이지
    @GetMapping("main")
    public String pubilcForm(@AuthenticationPrincipal UserDetails user, Model m) {
    	
    	Member member = service.getUser(user.getUsername());
    	m.addAttribute("member", member);
        
    	return "main";
    }
     
}