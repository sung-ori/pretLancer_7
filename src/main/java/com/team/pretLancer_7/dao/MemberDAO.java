package com.team.pretLancer_7.dao;

import org.apache.ibatis.annotations.Mapper;

import com.team.pretLancer_7.domain.Member;
import com.team.pretLancer_7.domain.MyPage;

@Mapper
public interface MemberDAO {

	// 회원가입 기능
	public int insertOne(Member m);

	// 1명의 회원정보 조회
	public Member selectOne(String searchid);
	
	// 마이페이지 안 소개글 , 사진 조회
	public MyPage selectOneMyPage(String memberId);
}
