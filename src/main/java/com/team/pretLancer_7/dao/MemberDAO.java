package com.team.pretLancer_7.dao;

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
	
	
	// 마이페이지 번역능력 가져오기
	public Ability getAbility(String username);
	
	
	// 캐쉬 및 신뢰도(경험치) 관련 기능은 아래쪽
	
	// 연습 문제 성공 시 경험치를 받음
	public void getExExam(String Memberid);
	
	// 의뢰 요청 시 포인트 차감
	public void usePoint(Member m);
	
	// 의뢰 취소 시 포인트 돌려줌
	public void cancelPoint(Member m);

	public int changeNick(Member member);

	public int checkPoint(Member member);

	public int minusPoint(Member member);
	
	// 단문 번역 성공 시 경험치와 포인트 받음
	public void getPointExS(Member m);
	
	// 중문 번역 성공 시 경험치와 포인트 받음
	public void getPointExM(Member m);
	
	// 경험치가 넘어가면 레벨업
	public void levelUpC(String memberid);
	public void levelUpB(String memberid);
	public void levelUpA(String memberid);
	
	// 단문 평가 시 경험치를 받음 (번역의 1/10)
	public void getExES(String memberid);
	
	// 중문 평가 시 경험치를 받음 (번역의 1/10)
	public void getExEM(String memberid);
	
}
