package com.team.pretLancer_7.service;

import com.team.pretLancer_7.domain.Request_M;
import com.team.pretLancer_7.domain.Request_S;

public interface RequestService {

	void insertRequest_S(Request_S r);

	void cancelRequest_S(Request_S r);

	void insertRequest_M(Request_M r);

	void cancelRequest_M(Request_M r);
	
	// 번역하기 버튼을 눌렀을 시 테이블에 들어있는 의뢰 중 하나를 가져옴
	Request_S choiceRS();
	
}
