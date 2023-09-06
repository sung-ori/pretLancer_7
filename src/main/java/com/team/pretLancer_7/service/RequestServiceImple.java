package com.team.pretLancer_7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.pretLancer_7.dao.MemberDAO;
import com.team.pretLancer_7.dao.RequestDAO;
import com.team.pretLancer_7.domain.Member;
import com.team.pretLancer_7.domain.Request_M;
import com.team.pretLancer_7.domain.Request_S;

@Service
public class RequestServiceImple implements RequestService {

	@Autowired
	RequestDAO Rdao;
	MemberDAO Mdao;
	
	@Override
	public void insertRequest_S(Request_S r) {
		// request_s 테이블에 저장
		Rdao.insertRS(r);
		
		// 사용한 포인트만큼 pret_member에서 차감
		Member m = new Member();
		m.setMemberid(r.getMemberid());
		m.setCash(r.getCash());
		Mdao.usePoint(m);
		
	}

	@Override
	public void deleteRequest_S(Request_S r) {
		Rdao.deleteRS(r);
		
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
		
		// 사용한 포인트만큼 pret_member에서 차감
		Member m = new Member();
		m.setMemberid(r.getMemberid());
		m.setCash(r.getCash());
		Mdao.usePoint(m);
		
	}

	@Override
	public void deleteRequest_M(Request_M r) {
		Rdao.deleteRM(r);
		
		// 취소 됐을 때 포인트를 돌려줌
		Request_S re = Rdao.selectRM(r);
		Member m = new Member();
		m.setMemberid(re.getMemberid());
		m.setCash(re.getCash());
		Mdao.cancelPoint(m);
	}
	
}
