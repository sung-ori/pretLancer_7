package com.team.pretLancer_7.controller;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team.pretLancer_7.domain.Request_S;
import com.team.pretLancer_7.domain.Translated;
import com.team.pretLancer_7.service.RequestService;
import com.team.pretLancer_7.service.TranslatedService;

@Controller
@RequestMapping("translated")
public class TranslatedController {
	
	@Autowired
	RequestService RService;
	TranslatedService TService;
	
	@GetMapping({" ", "/"})
	public String transHome() {
		
		return "translatedForm/transForm";
	}
	
	// 단문 번역하기 버튼 눌렀을 시 작동 - 
	@PostMapping("insertTS")
	public String insertTS(@AuthenticationPrincipal UserDetails user, Translated t) {
		// 의뢰를 받았을 시 의뢰테이블에서 가장 최근의 글을 하나 선택
		//Request_S rs = RService.selectRS();
		// 의뢰를 받았을 때, 번역 페이지로 return 시켜야함
		// 의뢰를 받았을 시 테이블에 정보를 저장하고, 의뢰와 번역 테이블의 값을 변환시켜야함
		t.setMemberid(user.getUsername());
		
		return "trnaslatedForm/transPage";
	}
	
}
