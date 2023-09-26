package com.team.pretLancer_7.service;

import java.util.List;

import com.team.pretLancer_7.domain.Evaluation_M;
import com.team.pretLancer_7.domain.Evaluation_S;
import com.team.pretLancer_7.domain.Member;
import com.team.pretLancer_7.domain.Translated_M;
import com.team.pretLancer_7.domain.Translated_S;

public interface EvaluationService {

	Translated_S getES();
	
	Translated_M getEM();

	void insertES(Evaluation_S es);

	void insertEM(Evaluation_M em);

}
