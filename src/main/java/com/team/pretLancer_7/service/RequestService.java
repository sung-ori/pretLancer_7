package com.team.pretLancer_7.service;

import com.team.pretLancer_7.domain.Request_M;
import com.team.pretLancer_7.domain.Request_S;

public interface RequestService {

	void insertRequest_S(Request_S r);

	void deleteRequest_S(Request_S r);

	void insertRequest_M(Request_M r);

	void deleteRequest_M(Request_M r);
	
}
