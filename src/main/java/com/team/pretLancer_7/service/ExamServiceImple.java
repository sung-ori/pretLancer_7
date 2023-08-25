package com.team.pretLancer_7.service;

import java.util.ArrayList;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.pretLancer_7.dao.ExamDAO;
import com.team.pretLancer_7.domain.Exam;
import com.team.pretLancer_7.test.AnswerTest;

@Service
public class ExamServiceImple implements ExamService {

	@Autowired
	ExamDAO dao;
	
	@Autowired
	AnswerTest test;
	
	@Override
	public Exam getQuestion(Exam ex) {
		ArrayList<Exam> list = dao.selectExam(ex);
		
		Exam randomExam = getRandomExam(list);
        if (randomExam != null) {
            System.out.println("선택된 랜덤 문제: " + randomExam.toString());
        } else {
            System.out.println("문제가 없습니다.");
        }
		
		return randomExam;
	}
	
	public static Exam getRandomExam(ArrayList<Exam> examList) {
        if (examList == null || examList.isEmpty()) {
            return null;
        }
        
        Random rand = new Random();
        int randomIndex = rand.nextInt(examList.size());
        
        return examList.get(randomIndex);
    }

	@Override
	public int getAnswer(Exam ex) {
		Exam answer = dao.findAnswer(ex);
		double similarity = test.findSimilarity(answer.getExam_answer(),ex.getMy_answer());
        System.out.println(similarity);
		int cnt;
        if (similarity > 0.7) {
        	cnt = 1;
        }
        else {
        	cnt = 0;
        }
		return cnt;
	}
	
	
	
	
}
