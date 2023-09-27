package com.team.pretLancer_7.service;

import java.util.List;

import com.team.pretLancer_7.domain.Request_M;
import com.team.pretLancer_7.domain.Request_S;

public interface RequestService {

	void insertRequest_S(Request_S r);

	void cancelRequest_S(Request_S r);

	void insertRequest_M(Request_M r);

	void cancelRequest_M(Request_M r);
	
	// 번역하기 버튼을 눌렀을 시 단문테이블에 들어있는 의뢰 중 하나를 가져옴
	Request_S choiceRS();
	
	// 번역하기 버튼을 눌렀을 시 중문테이블에 들어있는 의뢰 중 하나를 가져옴
	Request_M choiceRM();

	Request_S myorderS(String username);

	Request_M myorderM(String username);
	
	// 메인화면에 보여줄 용도
	int RequestCount();
	int TranslatedCountS();
	int TranslatedCountM();
	int EvaluationCount();

	List<Request_S> getRequestS(String username);
	List<Request_M> getRequestM(String username);

}
