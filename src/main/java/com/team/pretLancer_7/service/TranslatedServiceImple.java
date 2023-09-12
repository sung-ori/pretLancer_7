package com.team.pretLancer_7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.pretLancer_7.dao.RequestDAO;
import com.team.pretLancer_7.dao.TranslatedDAO;
import com.team.pretLancer_7.domain.Translated_M;
import com.team.pretLancer_7.domain.Translated_S;

@Service
public class TranslatedServiceImple implements TranslatedService {

	@Autowired
	RequestDAO Rdao;
	TranslatedDAO Tdao;
	
	@Override
	public void insertTS(Translated_S ts) {
		Tdao.insetTS(ts);
		Rdao.updateMem2_S(ts);
	}

	@Override
	public void insertTM(Translated_M tm) {
		Tdao.insetTM(tm);
		Rdao.updateMem2_M(tm);
	}

	
	
}
