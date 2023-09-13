package com.team.pretLancer_7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.pretLancer_7.dao.RequestDAO;
import com.team.pretLancer_7.dao.TranslatedDAO;
import com.team.pretLancer_7.domain.Request_M;
import com.team.pretLancer_7.domain.Request_S;
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
	public Translated_S insertTS(Request_S rs) {
		Tdao.insertTS(rs);
		Rdao.updateMem2_S(rs);
		return Tdao.selectTS(rs.getRequestnum_s());
	}

	@Override
	public Translated_M insertTM(Request_M rm) {
		Tdao.insertTM(rm);
		Rdao.updateMem2_M(rm);
		return Tdao.selectTM(rm.getRequestnum_m());
	}

	@Override
	public void submitTS(Translated_S ts) {
		Tdao.submitTS(ts);
		Rdao.updateCon_S(ts);
	}

	@Override
	public void submitTM(Translated_M tm) {
		Tdao.submitTM(tm);
		Rdao.updateCon_M(tm);
	}

	
	
}
