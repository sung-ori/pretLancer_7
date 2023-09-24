 package com.team.pretLancer_7.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.pretLancer_7.dao.EvaluationDAO;
import com.team.pretLancer_7.dao.MemberDAO;
import com.team.pretLancer_7.dao.RequestDAO;
import com.team.pretLancer_7.domain.Evaluation_M;
import com.team.pretLancer_7.domain.Evaluation_S;
import com.team.pretLancer_7.domain.Member;
import com.team.pretLancer_7.domain.Request_M;
import com.team.pretLancer_7.domain.Request_S;
import com.team.pretLancer_7.domain.Translated_M;
import com.team.pretLancer_7.domain.Translated_S;

@Service
public class EvaluationServiceImple implements EvaluationService {

	@Autowired
	EvaluationDAO Edao;
	
	@Autowired
	MemberDAO Mdao;
	
	@Autowired
	RequestDAO Rdao;
	
	@Override
	public Translated_S getES() {
		return Edao.getES();
	}

	@Override
	public Translated_M getEM() {
		return Edao.getEM();
	}

	@Override
	public void insertES(Evaluation_S es) {
		Edao.insertES(es);
		Edao.evUpS(es.getTranslatednum_s());
		Translated_S ts = Edao.evCheckS(es.getTranslatednum_s());
		long countOfY = 0;
		if (ts.getEvaluationvalue_s() == 5) {
			List<Evaluation_S> list = Edao.getListES(ts.getTranslatednum_s());
			countOfY = list.stream()
			            .filter(evaluation -> "Y".equals(evaluation.getEvaluationsuccess()))
			            .count();
			if (countOfY >= 4) {
				Request_S rs = Rdao.getRequestS(es.getRequestnum_s());
				rs.setTranslatedcontent_s(ts.getTranslatedcontent_s());
				Edao.completeS(rs);
				Member m = new Member();
				m.setCash(rs.getCash());
				m.setMemberid(rs.getMemberid2());
				Mdao.getPointExS(m);
				// 레벨업 체크
				Member exCheck = Mdao.selectOne(m.getMemberid());
				if (exCheck.getMem_ex() >= 50000) {
		            switch (exCheck.getMem_level()) {
		                case "D":
		                    Mdao.levelUpC(m.getMemberid());
		                    break;
		                case "C":
		                    Mdao.levelUpB(m.getMemberid());
		                    break;
		                case "B":
		                    Mdao.levelUpA(m.getMemberid());
		                    break;
		                default:
		                    // Mem_level이 'D', 'C', 'B' 중 하나가 아닌 경우 취소
		                    break;
		            }
		        }
			}
			else {
				Edao.failedS(es.getRequestnum_s());
			}
		}
	}

	@Override
	public void insertEM(Evaluation_M em) {
		Edao.insertEM(em);
		Edao.evUpM(em.getTranslatednum_m());
		Translated_M tm = Edao.evCheckM(em.getTranslatednum_m());
		long countOfY = 0;
		if (tm.getEvaluationvalue_m() == 5) {
			List<Evaluation_M> list = Edao.getListEM(tm.getTranslatednum_m());
			countOfY = list.stream()
			            .filter(evaluation -> "Y".equals(evaluation.getEvaluationsuccess()))
			            .count();
			if (countOfY >= 4) {
				Request_M rm = Rdao.getRequestM(em.getRequestnum_m());
				rm.setTranslatedcontent_m(tm.getTranslatedcontent_m());
				Edao.completeM(rm);
				Member m = new Member();
				m.setCash(rm.getCash());
				m.setMemberid(rm.getMemberid2());
				Mdao.getPointExS(m);
				// 레벨업 체크
				Member exCheck = Mdao.selectOne(m.getMemberid());
				if (exCheck.getMem_ex() >= 50000) {
		            switch (exCheck.getMem_level()) {
		                case "D":
		                    Mdao.levelUpC(m.getMemberid());
		                    break;
		                case "C":
		                    Mdao.levelUpB(m.getMemberid());
		                    break;
		                case "B":
		                    Mdao.levelUpA(m.getMemberid());
		                    break;
		                default:
		                    // Mem_level이 'D', 'C', 'B' 중 하나가 아닌 경우 취소
		                    break;
		            }
		        }
			}
			else {
				Edao.failedM(em.getRequestnum_m());
			}
		}	
	}
	
}
