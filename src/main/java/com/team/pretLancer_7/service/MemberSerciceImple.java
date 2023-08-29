package com.team.pretLancer_7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.team.pretLancer_7.dao.MemberDAO;
import com.team.pretLancer_7.domain.Member;

@Service
public class MemberSerciceImple implements MemberService {
	
	@Autowired
	MemberDAO dao;

	@Autowired
	PasswordEncoder encoder;

	@Override
	public int insertMember(Member m) {
		m.setMemberpw(encoder.encode(m.getMemberpw()));
		return dao.insertOne(m);
	}

	@Override
	public boolean idcheck(String searchid) {
		
		return dao.selectOne(searchid) == null;
	}

	
	
}
