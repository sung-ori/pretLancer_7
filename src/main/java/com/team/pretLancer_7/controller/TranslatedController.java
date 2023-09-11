package com.team.pretLancer_7.controller;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
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
	@GetMapping("insertTS")
	public String insertTS(@AuthenticationPrincipal UserDetails user, Model m) {
		// 의뢰를 받았을 시 의뢰테이블에서 가장 최근의 글을 하나 선택
		Request_S rs = RService.choiceRS();
		
		// 의뢰를 받았을 시 테이블에 정보를 저장하고, 의뢰테이블의 - memberid2 - 값을 변환
		Translated_S ts = new Translated_S();
		ts.setRequestnum_s(rs.getRequestnum_s());
		ts.setRequestcontent_s(rs.getRequestcontent_s());
		ts.setMemberid(user.getUsername());
		TService.insertTS(ts);
		
		m.addAttribute("request", rs);

		return "trnaslatedForm/transPage";
	}
	
	// 중문 번역하기 버튼 눌렀을 시 작동 - 
		@GetMapping("insertTS")
		public String insertTM(@AuthenticationPrincipal UserDetails user, Model m) {
			// 의뢰를 받았을 시 의뢰테이블에서 가장 최근의 글을 하나 선택
			Request_M rm = RService.choiceRM();
			
			// 의뢰를 받았을 시 테이블에 정보를 저장하고, 의뢰테이블의 - memberid2 - 값을 변환
			Translated_M tm = new Translated_M();
			tm.setRequestnum_m(rm.getRequestnum_m());
			tm.setRequestcontent_m(rm.getRequestcontent_m());
			tm.setMemberid(user.getUsername());
			TService.insertTM(tm);
			
			m.addAttribute("request", rm);

			return "trnaslatedForm/transPage";
		}
		
	// 단문 번역 제출하기 버튼 눌렀을 시 작동
	
		
	// 중문 번역 제출하기 버튼 눌렀을 시 작동
	
}
