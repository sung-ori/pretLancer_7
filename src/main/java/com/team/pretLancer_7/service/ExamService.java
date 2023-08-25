package com.team.pretLancer_7.service;

import com.team.pretLancer_7.domain.Exam;

public interface ExamService {

	Exam getQuestion(Exam ex);

	int getAnswer(Exam ex);

}
