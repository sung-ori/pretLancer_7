 package com.team.pretLancer_7.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.pretLancer_7.dao.AbilityDAO;
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

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EvaluationServiceImple implements EvaluationService {

   @Autowired
   EvaluationDAO Edao;
   
   @Autowired
   MemberDAO Mdao;
   
   @Autowired
   RequestDAO Rdao;
   
   @Autowired
   AbilityDAO Adao;
   
   @Override
   public Translated_S getES(String username) {
      return Edao.getES(username);
   }

   @Override
   public Translated_M getEM(String username) {
      return Edao.getEM(username);
   }

   @Override
   public void insertES(Evaluation_S es) {
      Edao.insertES(es);
      Mdao.getExES(es.getMemberid());
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
            
            // 평가가 'Y'인 경우 번역이 성공
            for (Evaluation_S evaluation : list) {
                String memberid = evaluation.getMemberid();
                String evaluationsuccess = evaluation.getEvaluationsuccess();

                if ("Y".equals(evaluationsuccess)) {
                   Adao.EsucceedUp(memberid);
                } else if ("N".equals(evaluationsuccess)) {
                   Adao.EfailedUp(memberid);
                }
            }
            
            // evaluationcondition_s를 'Y'로 변환
            Edao.completeES(ts.getTranslatednum_s());
            
            Member m = new Member();
            m.setCash(rs.getCash());
            m.setMemberid(rs.getMemberid2());
            Mdao.getPointExS(m);
            Adao.SsucceedUp(rs.getMemberid2());
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
            // 번역 실패 처리 후, reuqest_s 테이블을 초기화
            Edao.failedTS(es.getRequestnum_s());
            // 평가가 'N'인 경우 번역이 성공
            for (Evaluation_S evaluation : list) {
                String memberid = evaluation.getMemberid();
                String evaluationsuccess = evaluation.getEvaluationsuccess();

                if ("N".equals(evaluationsuccess)) {
                   Adao.EsucceedUp(memberid);
                } else if ("Y".equals(evaluationsuccess)) {
                   Adao.EfailedUp(memberid);
                }
            }
            // evaluationcondition_s를 'Y'로 처리함
            Edao.completeES(ts.getTranslatednum_s());
            // ability 테이블에서 번역실패 횟수 증가
            Adao.SfailedUp(ts.getMemberid());
         }
      }
   }

   @Override
   public void insertEM(Evaluation_M em) {
      Edao.insertEM(em);
      Mdao.getExEM(em.getMemberid());
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
            log.error("번역이 완료되야 합니다 {}", rm);
            Edao.completeM(rm);
            
            // 평가가 'Y'인 경우 번역이 성공
            for (Evaluation_M evaluation : list) {
                String memberid = evaluation.getMemberid();
                String evaluationsuccess = evaluation.getEvaluationsuccess();

                if ("Y".equals(evaluationsuccess)) {
                   Adao.EsucceedUp(memberid);
                } else if ("N".equals(evaluationsuccess)) {
                   Adao.EfailedUp(memberid);
                }
            }
            
            // evaluationcondition_m를 'Y'로 변환
            Edao.completeEM(tm.getTranslatednum_m());
            
            Member m = new Member();
            log.error("Cash를 넣어야 합니다 {}", m);
            m.setCash(rm.getCash());
            m.setMemberid(rm.getMemberid2());
            Mdao.getPointExM(m);
            Adao.MsucceedUp(rm.getMemberid2());
            // 레벨업 체크
            Member exCheck = Mdao.selectOne(m.getMemberid());
            log.error("레벨업을 해야합니다 {}", exCheck);
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
            Edao.failedTM(em.getRequestnum_m());
            
            // 평가가 'N'인 경우 번역이 성공
            for (Evaluation_M evaluation : list) {
                String memberid = evaluation.getMemberid();
                String evaluationsuccess = evaluation.getEvaluationsuccess();

				    if ("N".equals(evaluationsuccess)) {
				    	Adao.EsucceedUp(memberid);
				    } else if ("Y".equals(evaluationsuccess)) {
				    	Adao.EfailedUp(memberid);
				    }
				}
				
				Edao.completeEM(tm.getTranslatednum_m());
				Adao.MfailedUp(tm.getMemberid());
			}
		}	
	}

	@Override
	public Request_S getRS(int request_num) {
		return Rdao.getRequestS(request_num);
	}

	@Override
	public Request_M getRM(int request_num) {
		return Rdao.getRequestM(request_num);
	}
   
   

}