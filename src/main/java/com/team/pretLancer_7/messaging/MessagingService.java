package com.team.pretLancer_7.messaging;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.pretLancer_7.dao.CommunityDAO;
import com.team.pretLancer_7.dao.LongDAO;
import com.team.pretLancer_7.dao.RequestDAO;
import com.team.pretLancer_7.dao.TranslatedDAO;
import com.team.pretLancer_7.domain.Message;
import com.team.pretLancer_7.domain.Reply;
import com.team.pretLancer_7.domain.Request_L;
import com.team.pretLancer_7.domain.Translated_M;
import com.team.pretLancer_7.domain.Translated_S;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessagingService {
 
    @Autowired
    MessagingDAO dao;

    
    @Autowired
    CommunityDAO Cdao;
    @Autowired
    TranslatedDAO Tdao;
    @Autowired
    RequestDAO Rdao;
    @Autowired
    LongDAO Ldao;
    

    // 가입과 동시에 웰컴 메세지와 마이페이지 수정 메세지를 삽입한다.
    public void writeMP(String memberid) {
        String str ="";
        String userid = memberid;
        Message msg = new Message();
        String href = "";
        str += userid;
        str += "님 🎉프릿랜서에 가입하신걸 환영합니다🎉";
        str += " 마이프로필을 작성해주세요!";
        // 아직 수정 경로가 없어서 마이프로필로 접근
        href += "http://localhost:8888/pretLancer/my_page/MyProfile";
        
        msg.setType("MP");
        msg.setTo(userid);
        msg.setFrom("Admin");
        msg.setMessage(str);
        msg.setHref(href);

        dao.insertMessage(msg);
    }


    public void writeMT(String memberid) {
        String str ="";
        String userid = memberid;
        Message msg = new Message();
        String href = "";

        str += userid;
        str += "님! 번역 활동을 원하시면";
        str += " 튜토리얼을 완료해주세요!";
        // 튜토리얼 페이지로 이동
        href += "http://localhost:8888/pretLancer/tlanslated/tutorial";
        
        msg.setType("MT");
        msg.setTo(userid);
        msg.setFrom("Admin");
        msg.setMessage(str);
        msg.setHref(href);

        dao.insertMessage(msg);
    }

    // 레벨업 안내 / 아직 레벨업 기능이 없어 ㅎㅎ
    public void writeLV() {

    }

    // 댓글 달림 안내
    public void writeCR(Reply r) {

        String str ="";
        String userid = Cdao.selectOne(r.getBoardnum()).getMemberid();
        Message msg = new Message();
        String href = "";

        str += userid;
        str += "님의 커뮤니티 게시글에 ";
        str += r.getMemberid();
        str += "님의";
        str += " 댓글이 달렸습니다!";
        str += " 지금 확인해보세요!";
        
        // 해당 게시글로 이동
        href += "http://localhost:8888/pretLancer/community/read?boardnum=" + r.getBoardnum();
        
        msg.setType("CR");
        msg.setTo(userid);
        msg.setFrom("Admin");
        msg.setMessage(str);
        msg.setHref(href);

        dao.insertMessage(msg);
    }
    // 신고 5회가 누적되면 생성되는 메세지
    public void writeCP(int boardnum) {

        String str ="";
        String userid = Cdao.selectOne(boardnum).getMemberid();
        Message msg = new Message();
        String href = "";

        str += userid;
        str += "님의 커뮤니티 게시글이";
        str += " 신고조회수 5회 이상 누적되어";
        str += " 삭제되었습니다.";
        str += " 커뮤니티 이용규칙을 준수해주세요.";
        
        // 이동은 없지만
        // TODO: 이용약관 페이지로 가버려도 될 듯
        href += "";
        
        msg.setType("CR");
        msg.setTo(userid);
        msg.setFrom("Admin");
        msg.setMessage(str);
        msg.setHref(href);

        dao.insertMessage(msg);
    }

    // 단문 번역 완료 (평가 단계로 넘어간다는 뜻)
    public void writeSE(Translated_S ts) {

        String str ="";
        String userid = Rdao.SRS(ts.getRequestnum_s()).getMemberid();
        Message msg = new Message();
        String href = "";

        str += userid;
        str += "님이 요청하신";
        str += " 단문번역이";
        str += " 완료되어";
        str += " 이제 평가중입니다!.";
        
        // 해당 게시글로 이동
        href += "" ;
        
        msg.setType("CR");
        msg.setTo(userid);
        msg.setFrom("Admin");
        msg.setMessage(str);
        msg.setHref(href);

        dao.insertMessage(msg);
    }
    // 단문 번역이 평가 완료 되고 성공하는 알림 요청자가 결과 페이지를 제공
    public void writeSC() {

    }

    // 단문 번역이 평가 완료 되고 결과가 실패여서 변역가에게 실패를 알리는 페이지를 제공
    public void writeSF() {

    }
     // 중문 번역 완료 (평가 단계로 넘어간다는 뜻)
    public void writeME(Translated_M tm) {

        String str ="";
        String userid = Rdao.SRM(tm.getRequestnum_m()).getMemberid();
        Message msg = new Message();
        String href = "";

        str += userid;
        str += "님이 요청하신";
        str += " 중문번역이";
        str += " 완료되어";
        str += " 이제 평가중입니다!.";
        
        // 해당 게시글로 이동
        href += "" ;
        
        msg.setType("ME");
        msg.setTo(userid);
        msg.setFrom("Admin");
        msg.setMessage(str);
        msg.setHref(href);

        dao.insertMessage(msg);
    }

     // 중문 번역이 평가 완료 되고 성공하는 알림 요청자가 결과 페이지를 제공
    public void writeMC() {

    }

    // 중문 번역이 평가 완료 되고 결과가 실패여서 변역가에게 실패를 알리는 페이지를 제공
    public void writeMF() {

    }


    // 나에게 장문요청이 왔다는 알림
    public void writeLR(Request_L rql) {
        String str ="";
        String userid = rql.getMemberid2();
        Message msg = new Message();
        String href = "";

        str += userid;
        str += "님!";
        str += rql.getMemberid();
        str += "님에게서";
        str += " 장문번역📑 요청이 도착했습니다!";
        str += " 지금 확인해보세요!";
        
        // 해당 게시글로 이동
        href += "http://localhost:8888/pretLancer/long/requestToMe" ;
        
        msg.setType("LR");
        msg.setTo(userid);
        msg.setFrom("Admin");
        msg.setMessage(str);
        msg.setHref(href);

        dao.insertMessage(msg);
    }
    
    //내가 보낸 요청이 승낙받았다는 메세지
    public void writeLA(Map<String, String> map) {

        String str ="";
        String userid = Ldao.selectOneRequest_L(Integer.parseInt(map.get("requestnum"))).getMemberid();
        Message msg = new Message();
        String href = "";

        str += userid;
        str += "님! ";
        str += Ldao.selectOneRequest_L(Integer.parseInt(map.get("requestnum"))).getMemberid2();
        str += "님에게";
        str += " 보낸 장문번역📑 요청이 승낙받았습니다!";        

        // TODO: 아직 없지만 내 요청 목록 페이지로 가도 좋을 듯
        href += "http://localhost:8888/pretLancer/long/myRequest" ;
        
        msg.setType("LA");
        msg.setTo(userid);
        msg.setFrom("Admin");
        msg.setMessage(str);
        msg.setHref(href);

        dao.insertMessage(msg);
    }

    // 내가 보낸 요청이 거절당했다는 메세지
    public void writeLF(Map<String, String> map) {

        String str ="";
        String userid = Ldao.selectOneRequest_L(Integer.parseInt(map.get("requestnum"))).getMemberid();
        Message msg = new Message();
        String href = "";

        str += userid;
        str += "님! ";
        str += Ldao.selectOneRequest_L(Integer.parseInt(map.get("requestnum"))).getMemberid2();
        str += "님에게";
        str += " 보낸 장문번역📑 요청이 거절되었습니다.";        

        // TODO: 아직 없지만 내 요청 목록 페이지로 가도 좋을 듯
        href += "http://localhost:8888/pretLancer/long/myRequest" ;
        
        msg.setType("LF");
        msg.setTo(userid);
        msg.setFrom("Admin");
        msg.setMessage(str);
        msg.setHref(href);

        dao.insertMessage(msg);
    }

    // 내가 입찰한 경매가 낙찰받았다는 메세지
    public void writeLB(Map<String, String> map) {

        String str ="";
        String userid = Ldao.selectOneRequest_L(Integer.parseInt(map.get("requestnum"))).getMemberid2();
        Message msg = new Message();
        String href = "";

        str += userid;
        str += "님! ";
        str += Ldao.selectOneRequest_L(Integer.parseInt(map.get("requestnum"))).getMemberid();
        str += "님의";
        str += " 장문번역📑 경매 낙찰 받으셨습니다!";
        str += " 작업을 진행해주세요!";


        // TODO: 채팅 없으면 파일 다운로드 받는 페이지가 필요하다. 
        href += "" ;
        
        msg.setType("LB");
        msg.setTo(userid);
        msg.setFrom("Admin");
        msg.setMessage(str);
        msg.setHref(href);

        dao.insertMessage(msg);
    }


    // 내가 신청한 요청 / 경매 의뢰가 완료되었다는 알림
    public void writeLC() {
        // TODO: 채팅이 없으면 파일을 올릴 페이지가 필요하고 버튼을 누르면 처리 . 
    }

    // 내 메세지를 읽어온다. 
    public List<Message> getMyMessages(String userid) {

       return dao.selectUnclickedMessage(userid);
    }

    //TODO: 메세지를 출력하고, 클릭할 수 있도록 만드는데 왜 타임리프가 동작하지 않니?
    
}
