package com.team.pretLancer_7.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.team.pretLancer_7.domain.Ability;
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

	// 회원가입 시 마이프로필 테이블 생성
	public void insertMp(MyPage Mp);
	
	// 마이프로필 수정
	public int updateProfile(MyPage mp);
	
	// 회원정보 수정
	public int updateMember(Member m);
	
	// 회원가입 시 번역능력 테이블 생성
	public void insertAb(Ability ab);
	
	// 의뢰 요청 시 포인트 차감
	public void usePoint(Member m);
	
	// 의뢰 취소 시 포인트 돌려줌
	public void cancelPoint(Member m);
	
}
