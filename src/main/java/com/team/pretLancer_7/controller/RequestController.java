package com.team.pretLancer_7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team.pretLancer_7.domain.Member;
import com.team.pretLancer_7.domain.Request_M;
import com.team.pretLancer_7.domain.Request_S;
import com.team.pretLancer_7.service.MemberService;
import com.team.pretLancer_7.service.RequestService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("request")
public class RequestController {
	
	@Autowired
	RequestService service;
	
	@Autowired
	MemberService Mservice;

	@GetMapping("main")
	public String requestPage() {
		
		return "/requestForm/requestPage";
	}
	
	@PostMapping("insertRS")
	public String insertRequest_S(@AuthenticationPrincipal UserDetails user, Request_S r) {
		r.setMemberid(user.getUsername());
		Member member = Mservice.getUser(user.getUsername());
		if (member.getCash() < r.getCash()) {
			return "errorForm/Nocash";
		}
		log.error("Request_s 객체 {}", r);
		service.insertRequest_S(r);
		return "main3";
	}
	
	@GetMapping("cancelRS")
	public String cancelRequest_S(@AuthenticationPrincipal UserDetails user, Request_S r) {
		r.setMemberid(user.getUsername());
		service.cancelRequest_S(r);
		return "redirect:/";
	}
	
	@PostMapping("insertRM")
	public String insertRequest_M(@AuthenticationPrincipal UserDetails user, Request_M r) {
		r.setMemberid(user.getUsername());
		Member member = Mservice.getUser(user.getUsername());
		if (member.getCash() < r.getCash()) {
			return "errorForm/Nocash";
		}
		service.insertRequest_M(r);
		return "redirect:/";
	}
	
	@GetMapping("cancelRM")
	public String cancelRequest_M(@AuthenticationPrincipal UserDetails user, Request_M r) {
		r.setMemberid(user.getUsername());
		Member member = Mservice.getUser(user.getUsername());
		if (member.getCash() < r.getCash()) {
			return "errorForm/Nocash";
		}
		service.cancelRequest_M(r);
		return "redirect:/";
	}

}
