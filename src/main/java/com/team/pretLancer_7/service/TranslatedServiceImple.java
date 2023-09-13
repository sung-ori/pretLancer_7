package com.team.pretLancer_7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.pretLancer_7.dao.RequestDAO;
import com.team.pretLancer_7.dao.TranslatedDAO;
import com.team.pretLancer_7.domain.Translated_M;
import com.team.pretLancer_7.domain.Translated_S;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TranslatedServiceImple implements TranslatedService {

	@Autowired
	RequestDAO Rdao;
	
	@Autowired
	TranslatedDAO Tdao;
	
	@Override
	public void insertTS(Translated_S ts) {
		Tdao.insertTS(ts);
		Rdao.updateMem2_S(ts);		
	}

	@Override
	public void insertTM(Translated_M tm) {
		Tdao.insertTM(tm);
		Rdao.updateMem2_M(tm);
	}

	@Override
	public void submitTS(Translated_S ts) {
		Tdao.submitTS(ts);
	}

	
	
}
