package com.team.pretLancer_7.service;

import com.team.pretLancer_7.domain.Exam;
import com.team.pretLancer_7.domain.Member;

public interface ExamService {

	Exam getQuestion(Exam ex);

	int getAnswerTu(Exam ex);

	Member getMemberOne(String memberid);

	void tutorialCheck(String memberid);

	int getAnswerEx(Exam ex);
	
	void getTutorialUp(String memberid);

	void getTutorialUp(String memberid);

	//에이젝스 부분
	//int getExamInfo(Exam ex);
}
