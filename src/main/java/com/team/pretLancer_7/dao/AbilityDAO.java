package com.team.pretLancer_7.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.team.pretLancer_7.domain.Ability;
import com.team.pretLancer_7.domain.Member;

@Mapper
public interface AbilityDAO {

	// 연습문제
	public void PsucceedUp(String memberid);
	public void PfailedUp(String memberid);
	
	// 단문
	public void SsucceedUp(String memberid);
	public void SfailedUp(String memberid);
	
	// 중문
	public void MsucceedUp(String memberid);
	public void MfailedUp(String memberid);
	
	// 장문
	public void LsucceedUp(String memberid);
	public void LfailedUp(String memberid);
	
	// 평가
	public void EsucceedUp(String memberid);
	public void EfailedUp(String memberid);
	
	// 가장 연습문제 많이 푼, 가장 번역 많이 한, 가장 평가 많이 한 사람
	public List<Ability> ExamAlot();
	public List<Ability> TranslatedAlotS();
	public List<Ability> TranslatedAlotM();
	public List<Ability> EvaluationAlot();
	
}
