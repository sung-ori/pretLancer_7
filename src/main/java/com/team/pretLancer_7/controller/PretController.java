package com.team.pretLancer_7.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("pret")
@Slf4j
public class PretController {

	// 단문, 중문, 장문 번역 관련 Controller
	
	@GetMapping("mainpage")
	public String pretMain(@AuthenticationPrincipal UserDetails user, Model m) {
		
		
		return "pretForm/mainPage";
	}
}
