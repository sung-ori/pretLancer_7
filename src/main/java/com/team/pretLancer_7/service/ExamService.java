package com.team.pretLancer_7.service;

import com.team.pretLancer_7.domain.Exam;
import com.team.pretLancer_7.domain.Member;

public interface ExamService {

	Exam getQuestion(Exam ex);

	int getAnswer(Exam ex);

	Member getMemberOne(String memberid);

}
