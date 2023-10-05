package com.team.pretLancer_7.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.team.pretLancer_7.domain.Evaluation_M;
import com.team.pretLancer_7.domain.Evaluation_S;
import com.team.pretLancer_7.domain.Member;
import com.team.pretLancer_7.domain.Request_M;
import com.team.pretLancer_7.domain.Request_S;
import com.team.pretLancer_7.domain.Translated_M;
import com.team.pretLancer_7.domain.Translated_S;

@Mapper
public interface EvaluationDAO {

	
	Translated_S getES(String username);
	
	Translated_M getEM(String username);
	

	void insertES(Evaluation_S es);

	void insertEM(Evaluation_M em);

	
	void evUpS(int translatednum_s);
	
	void evUpM(int translatednum_m);
	
	
	// 단문 평가 확인
	Translated_S evCheckS(int translatednum_s);

	List<Evaluation_S> getListES(int translatednum_s);

	void completeS(Request_S rs);
	
	void completeES(int translatednum_s);

	void failedTS(int requestnum_s);
	
	
	// 중문 평가 확인
	Translated_M evCheckM(int translatednum_m);
	
	List<Evaluation_M> getListEM(int translatednum_m);
	
	void completeEM(int translatednum_m);

	void completeM(Request_M rm);

	void failedTM(int requestnum_m);

	// 마이페이지 내가 맡은 번역 리스트 평가 탈락 이유 가져오기
	List<Evaluation_S> getWhyRS(int translatednum_s);
	List<Evaluation_M> getWhyRM(int translatednum_m);

}
