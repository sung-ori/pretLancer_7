package com.team.pretLancer_7.dao;

import org.apache.ibatis.annotations.Mapper;

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
	
}
