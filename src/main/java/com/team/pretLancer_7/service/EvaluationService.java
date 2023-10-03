package com.team.pretLancer_7.service;

import java.util.List;

import com.team.pretLancer_7.domain.Evaluation_M;
import com.team.pretLancer_7.domain.Evaluation_S;
import com.team.pretLancer_7.domain.Member;
import com.team.pretLancer_7.domain.Request_M;
import com.team.pretLancer_7.domain.Request_S;
import com.team.pretLancer_7.domain.Translated_M;
import com.team.pretLancer_7.domain.Translated_S;

public interface EvaluationService {

	Translated_S getES(String username);
	
	Translated_M getEM(String username);

	void insertES(Evaluation_S es);

	void insertEM(Evaluation_M em);

	Request_S getRS(int request_num);

	Request_M getRM(int request_num);

}
