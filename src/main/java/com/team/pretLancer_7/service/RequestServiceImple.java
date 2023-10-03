package com.team.pretLancer_7.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.pretLancer_7.dao.MemberDAO;
import com.team.pretLancer_7.dao.RequestDAO;
import com.team.pretLancer_7.domain.Member;
import com.team.pretLancer_7.domain.Request_M;
import com.team.pretLancer_7.domain.Request_S;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RequestServiceImple implements RequestService {

	@Autowired
	RequestDAO Rdao;
	
	@Autowired
	MemberDAO Mdao;
	
	@Override
	public void insertRequest_S(Request_S r) {
		// request_s 테이블에 저장
		log.error("dao 객체 r 여부 확인 {}", r);
		Rdao.insertRS(r);
		
		// 사용한 포인트만큼 pret_member에서 차감
		Member m = new Member();
		log.error("id: {}, cash:{}", r.getMemberid(), r.getCash());
		m.setMemberid(r.getMemberid());
		m.setCash(r.getCash());
		Mdao.usePoint(m);
	}

	@Override
	public void cancelRequest_S(Request_S r) {
		Rdao.cancelRS(r);
		
		// 취소 됐을 때 포인트를 돌려줌
		Request_S re = Rdao.selectRS(r);
		Member m = new Member();
		m.setMemberid(re.getMemberid());
		m.setCash(re.getCash());
		Mdao.cancelPoint(m);
	}

	@Override
	public void insertRequest_M(Request_M r) {
		// request_s 테이블에 저장
		Rdao.insertRM(r);
		
		// 사용한 캐쉬만큼 pret_member에서 차감
		Member m = new Member();
		m.setMemberid(r.getMemberid());
		log.error("{}", m);
		m.setCash(r.getCash());
		Mdao.usePoint(m);
		
	}

	@Override
	public void cancelRequest_M(Request_M r) {
		Rdao.cancelRM(r);
		
		// 취소 됐을 때 포인트를 돌려줌
		Request_S re = Rdao.selectRM(r);
		Member m = new Member();
		m.setMemberid(re.getMemberid());
		m.setCash(re.getCash());
		Mdao.cancelPoint(m);
	}

	@Override
	public Request_S choiceRS(String username) {
		return Rdao.choiceRS(username);
	}

	@Override
	public Request_M choiceRM(String username) {
		return Rdao.choiceRM(username);
	}

	@Override
	public Request_S myorderS(String username) {
		return Rdao.myorderS(username);
	}

	@Override
	public Request_M myorderM(String username) {
		return Rdao.myorderM(username);
	}

	@Override
	public int RequestCount() {
		int count = 0;
		int RS = Rdao.RScount();
		int RM = Rdao.RMcount();
		count = RS + RM;
		return count;
	}

	@Override
	public int TranslatedCountS() {
		return Rdao.TScount();
	}
	
	@Override
	public int TranslatedCountM() {
		return Rdao.TMcount();
	}

	@Override
	public int EvaluationCount() {
		int count = 0;
		int ES = Rdao.EScount();
		int EM = Rdao.EMcount();
		count = ES + EM;
		return count;
	}

	@Override
	public List<Request_S> getRequestS(String username) {		
		return Rdao.rsList2(username);
	}
	
	@Override
	public List<Request_M> getRequestM(String username) {
		return Rdao.rmList2(username);
	}

	
}
