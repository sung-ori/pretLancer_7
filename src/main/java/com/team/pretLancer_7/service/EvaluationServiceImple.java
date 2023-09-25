package com.team.pretLancer_7.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.pretLancer_7.dao.EvaluationDAO;
import com.team.pretLancer_7.domain.Evaluation_M;
import com.team.pretLancer_7.domain.Evaluation_S;
import com.team.pretLancer_7.domain.Translated_M;
import com.team.pretLancer_7.domain.Translated_S;
import com.team.pretLancer_7.messaging.MessagingService;

@Service
public class EvaluationServiceImple implements EvaluationService {

	@Autowired
	EvaluationDAO dao;
	@Autowired
	MessagingService msg;
	@Override
	public Translated_S getES() {
		return dao.getES();
	}

	@Override
	public Translated_M getEM() {
		return dao.getEM();
	}

	@Override
	public void insertES(Evaluation_S es) {
		dao.insertES(es);
		dao.evUpS(es.getTranslatednum_s());
		Translated_S ts = dao.evCheckS(es.getTranslatednum_s());
		long countOfY = 0;
		if (ts.getEvaluationvalue_s() == 5) {
			List<Evaluation_S> list = dao.getListES(ts.getTranslatednum_s());
			countOfY = list.stream()
			            .filter(evaluation -> "Y".equals(evaluation.getEvaluationsuccess()))
			            .count();
			if (countOfY >= 4) {
				dao.completeS(es.getRequestnum_s());
				// 메세지 작성
				msg.writeSC(ts);
			}
			else {
				dao.failedTS(es.getRequestnum_s());
				// 메세지 작성
				msg.writeSF(ts);
			}
		}
	}

	@Override
	public void insertEM(Evaluation_M em) {
		dao.insertEM(em);
		dao.evUpM(em.getTranslatednum_m());
		Translated_M tm = dao.evCheckM(em.getTranslatednum_m());
		long countOfY = 0;
		if (tm.getEvaluationvalue_m() == 5) {
			List<Evaluation_M> list = dao.getListEM(tm.getTranslatednum_m());
			countOfY = list.stream()
			            .filter(evaluation -> "Y".equals(evaluation.getEvaluationsuccess()))
			            .count();
			if (countOfY >= 4) {
				dao.completeM(em.getRequestnum_m());
				// 메세지 작성
				msg.writeMC(tm);
			}
			else {
				dao.failedS(em.getRequestnum_m());
				// 메세지 작성
				msg.writeMF(tm);
			}
		}	
	}
	
}
