package com.team.pretLancer_7.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.team.pretLancer_7.domain.Exam;
import com.team.pretLancer_7.domain.Member;

@Mapper
public interface ExamDAO {

	// 문제 목록을 가져옴
	public ArrayList<Exam> selectExam(Exam ex);
	
	// 문제번호로 해당 문제의 답을 가져옴
	public Exam findAnswer(Exam ex);

	public Member selectOne(String memberid);

	public void tutorialUp(String memberid);

	public void insertExamMember(Exam ex);

	public Exam getExamInfo(Exam ex);

	public void tutorialClear(String memberid);

	public void psucceedUp(String memberid);

	public void pfailedUp(String memberid);

}
