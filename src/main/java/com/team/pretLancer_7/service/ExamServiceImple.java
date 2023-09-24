package com.team.pretLancer_7.service;

import java.util.ArrayList;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.pretLancer_7.dao.AbilityDAO;
import com.team.pretLancer_7.dao.ExamDAO;
import com.team.pretLancer_7.dao.MemberDAO;
import com.team.pretLancer_7.domain.Exam;
import com.team.pretLancer_7.domain.Member;
import com.team.pretLancer_7.utill.AnswerUtill;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ExamServiceImple implements ExamService {

	@Autowired
	ExamDAO dao;
	
	@Autowired
	MemberDAO Mdao;
	
	@Autowired
	AbilityDAO Adao;
	
	
	@Override
	public Exam getQuestion(Exam ex) {
		ArrayList<Exam> list = dao.selectExam(ex);
		log.error("List 객체 {}", list);
		
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
	public int getAnswerTu(Exam ex) {
		Exam answer = dao.findAnswer(ex);
		double similarity = AnswerUtill.findSimilarity(answer.getExam_answer(),ex.getMy_answer());
        System.out.println(similarity);
		int cnt;
        if (similarity > 0.7) {
        	cnt = 1;
        	dao.tutorialUp(ex.getMemberid());
        	dao.insertExamMember(ex);
        }
        else {
        	cnt = 0;
        }
        log.debug("similarity : {}", similarity);
		return cnt;
	}
	
	@Override
	public int getAnswerEx(Exam ex) {
		Exam answer = dao.findAnswer(ex);
		double similarity = AnswerUtill.findSimilarity(answer.getExam_answer(),ex.getMy_answer());
        System.out.println(similarity);
		int cnt;
        if (similarity > 0.7) {
        	cnt = 1;
        	Adao.PsucceedUp(ex.getMemberid());
        	dao.insertExamMember(ex);
        	Mdao.getExExam(ex.getMemberid());
        }
        else {
        	cnt = 0;
        	Adao.PfailedUp(ex.getMemberid());
        }
        log.debug("similarity : {}", similarity);
		return cnt;
	}

	// member 1명의 정보를 가져옴
	@Override
	public Member getMemberOne(String memberid) {
		return dao.selectOne(memberid);
	}

	// tutorial 'Y'로 변경
	@Override
	public void tutorialCheck(String memberid) {
		dao.tutorialClear(memberid);
		
	}

	@Override
	public void getTutorialUp(String memberid) {
		dao.tutorialUp(memberid);;
	}

	
	
	
	/*ajax용 
	@Override
	public int getExamInfo(Exam ex) {
		int cnt;
		if (dao.getExamInfo(ex) == null) {
			cnt = 0;
		} else
			cnt = 1;
		return cnt;
	}
	*/
	
	
}
