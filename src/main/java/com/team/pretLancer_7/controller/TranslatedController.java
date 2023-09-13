package com.team.pretLancer_7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team.pretLancer_7.domain.Request_M;
import com.team.pretLancer_7.domain.Request_S;
import com.team.pretLancer_7.domain.Translated_M;
import com.team.pretLancer_7.domain.Translated_S;
import com.team.pretLancer_7.service.RequestService;
import com.team.pretLancer_7.service.TranslatedService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("translated")
public class TranslatedController {
	
	@Autowired
	RequestService RService;
	
	@Autowired
	TranslatedService TService;
	
	@GetMapping("main")
	public String transHome() {
		
		return "translatedForm/TselectPage";
	}
	
	// 단문 번역하기 버튼 눌렀을 시 작동 - 
	@GetMapping("insertTS")
	public String insertTS(@AuthenticationPrincipal UserDetails user, Model m) {
		// 의뢰를 받았을 시 의뢰테이블에서 가장 최근의 글을 하나 선택
		Request_S rs = RService.choiceRS();
		rs.setMemberid2(user.getUsername());
		log.error("Request 객체 {}",rs);
		
		// 의뢰를 받았을 시 테이블에 정보를 저장하고, 의뢰테이블의 - memberid2 - 값을 변환
		Translated_S ts = TService.insertTS(rs);
		log.error("Translated 객체 {}",ts);
		
		m.addAttribute("translated", ts);

		return "translatedForm/translatedPageS";
	}
	
	
	// 중문 번역하기 버튼 눌렀을 시 작동 - 
	@GetMapping("insertTM")
	public String insertTM(@AuthenticationPrincipal UserDetails user, Model m) {
		// 의뢰를 받았을 시 의뢰테이블에서 가장 최근의 글을 하나 선택
		Request_M rm = RService.choiceRM();
		rm.setMemberid2(user.getUsername());
		log.error("Request 객체 {}",rm);
				
		// 의뢰를 받았을 시 테이블에 정보를 저장하고, 의뢰테이블의 - memberid2 - 값을 변환
		Translated_M tm = TService.insertTM(rm);
		log.error("Translated 객체 {}",tm);
				
		m.addAttribute("translated", tm);

		return "translatedForm/translatedPageM";
	}
		
	// 단문 번역 제출하기 버튼 눌렀을 시 작동
	@PostMapping("submitTS")
	public String submitTS(@AuthenticationPrincipal UserDetails user, Translated_S ts) {
		// 번역 내용 저장, condition = 'E' 상태
		ts.setMemberid(user.getUsername());
		log.error("Translated 객체에 들어있는 것 {}", ts);
		TService.submitTS(ts);
		
		return "redirect:/";
	}
		
	// 중문 번역 제출하기 버튼 눌렀을 시 작동
	@PostMapping("submitTM")
	public String submitTM(@AuthenticationPrincipal UserDetails user, Translated_M tm) {
		// 번역 내용 저장, condition = 'E' 상태
		tm.setMemberid(user.getUsername());
		log.error("Translated 객체에 들어있는 것 {}", tm);
		TService.submitTM(tm);
				
		return "redirect:/";
	}
	
}
