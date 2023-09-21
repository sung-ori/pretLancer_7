package com.team.pretLancer_7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team.pretLancer_7.domain.Member;
import com.team.pretLancer_7.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("pret")
@Slf4j
public class PretController {

	// 홈페이지 controller (유저 정보를 여기서 보내줘야함)
	@Autowired
	MemberService service;
	
	@GetMapping("mainpage")
	public String pretMain(@AuthenticationPrincipal UserDetails user, Model m) {
		Member member = service.getUser(user.getUsername());
		m.addAttribute("member", member);
		return "pretForm/mainPage";
	}
}
