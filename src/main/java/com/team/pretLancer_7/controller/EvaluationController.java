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
import com.team.pretLancer_7.domain.Translated_M;
import com.team.pretLancer_7.domain.Translated_S;
import com.team.pretLancer_7.service.EvaluationService;

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
	public String getES(Model m) {
		// 평가 갯수가 5개 이하인 번역된 내용을 가져옴
		Translated_S ts = service.getES();
		m.addAttribute("translated", ts);

		return "evaluationForm/evaluationPageS";
	}
	
	@GetMapping("getEM")
	public String getEM(Model m) {
		// 평가 갯수가 5개 이하인 번역된 내용을 가져옴
		Translated_M tm = service.getEM();
		m.addAttribute("translated", tm);
		
		return "evaluationForm/evaluationPageM";
	}
	
	@PostMapping("insertES")
	public String insertES(Evaluation_S es, @AuthenticationPrincipal UserDetails user) {
		// 평가 내용을 저장
		es.setMemberid(user.getUsername());
		service.insertES(es);
		
		return "redirect:/";
	}
	
	@PostMapping("insertEM")
	public String insertEM(Evaluation_M em, @AuthenticationPrincipal UserDetails user) {
		// 평가 내용을 저장
		em.setMemberid(user.getUsername());
		service.insertEM(em);
		
		return "redirect:/";
	}
	
}
