package com.team.pretLancer_7.dao;

import org.apache.ibatis.annotations.Mapper;

import com.team.pretLancer_7.domain.Member;

@Mapper
public interface MemberDAO {

	// 회원가입 기능	
	public int insertOne(Member m);

	// 1명의 회원정보 조회
	public Member selectOne(String searchid);
	
}
