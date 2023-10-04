package com.team.pretLancer_7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team.pretLancer_7.domain.Evaluation_M;
import com.team.pretLancer_7.domain.Evaluation_S;
import com.team.pretLancer_7.domain.Request_M;
import com.team.pretLancer_7.domain.Request_S;
import com.team.pretLancer_7.domain.Translated_M;
import com.team.pretLancer_7.domain.Translated_S;
import com.team.pretLancer_7.service.EvaluationService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("evaluation")
public class EvaluationController {
	
	@Autowired
	EvaluationService service;
	
	@GetMapping("main")
	public String evalhome () {
		
		
		return "evaluationForm/EselectPage";
	}
	
	@GetMapping("getES")
	public String getES(@AuthenticationPrincipal UserDetails user, Model m) {
		// 평가 갯수가 5개 이하인 번역된 내용을 가져옴
		Translated_S ts = service.getES(user.getUsername());
		if (ts == null) {
			return "errorForm/NoEvaluation";
		}
		
		int request_num = ts.getRequestnum_s();
		Request_S rs = service.getRS(request_num);
		
		
		
		m.addAttribute("translated", ts);
		m.addAttribute("request", rs);
		
		log.debug("ts 객체 : {}", ts);
		log.debug("rs 객체 : {}", rs);

		return "evaluationForm/evaluatedPageS";
	}
	
	@GetMapping("getEM")
	public String getEM(@AuthenticationPrincipal UserDetails user, Model m) {
		// 평가 갯수가 5개 이하인 번역된 내용을 가져옴
		Translated_M tm = service.getEM(user.getUsername());
		
		if (tm == null) {
			return "errorForm/NoEvaluation";
		}
		
		int request_num = tm.getRequestnum_m();
		Request_M rm = service.getRM(request_num);
		
		
		
		m.addAttribute("translated", tm);
		m.addAttribute("request", rm);
		
		return "evaluationForm/evaluatedPageM";
	}
	
	@PostMapping("insertES")
	public String insertES(Evaluation_S es, @AuthenticationPrincipal UserDetails user) {
		// 평가 내용을 저장
		es.setMemberid(user.getUsername());
		service.insertES(es);
		log.debug("es 객체 : {}", es);
		return "redirect:/evaluation/getES";
	}
	
	@PostMapping("insertEM")
	public String insertEM(Evaluation_M em, @AuthenticationPrincipal UserDetails user) {
		// 평가 내용을 저장
		em.setMemberid(user.getUsername());
		service.insertEM(em);
		
		return "redirect:/evaluation/getEM";
	}
	
}
