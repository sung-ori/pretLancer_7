package com.team.pretLancer_7.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.team.pretLancer_7.domain.Evaluation_M;
import com.team.pretLancer_7.domain.Evaluation_S;
import com.team.pretLancer_7.domain.Translated_M;
import com.team.pretLancer_7.domain.Translated_S;

@Mapper
public interface EvaluationDAO {

	
	Translated_S getES();
	
	Translated_M getEM();
	

	void insertES(Evaluation_S es);

	void insertEM(Evaluation_M em);

	
	void evUpS(int translatednum_s);
	
	void evUpM(int translatednum_m);
	
	
	// 단문 평가 확인
	Translated_S evCheckS(int translatednum_s);

	List<Evaluation_S> getListES(int translatednum_s);

	void completeS(int requestnum_s);

	void failedS(int requestnum_s);
	
	
	// 중문 평가 확인
	Translated_M evCheckM(int translatednum_m);
	
	List<Evaluation_M> getListEM(int translatednum_m);

	void completeM(int requestnum_m);

	void failedM(int requestnum_m);

}