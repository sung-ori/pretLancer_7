package com.team.pretLancer_7.websocket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.thymeleaf.util.StringUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class EchoHandler extends TextWebSocketHandler {

    // 전체 로그인 유저
    private List<WebSocketSession> sessions = new ArrayList();
    // 1대 1 매핑
    private Map<String, WebSocketSession> userSessionMap = new HashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("소켓 연결");
        sessions.add(session);
        log.info(sendPushUsername(session));

        String senderId = sendPushUsername(session);
        userSessionMap.put(senderId, session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info("session = " + sendPushUsername(session));
        String msg = message.getPayload();
        // js에서 넘어온 메세지
        log.info("msg = " + msg);

        if (!StringUtils.isEmpty(msg)) {
            String[] strs = msg.split(",");

            if (strs != null && strs.length == 5) {
                String pushCategory = strs[0];

                String replyWriter = strs[1];

                String sendedPushUser = strs[2];
            
                String boardId = strs[3];

                String title = strs[4];

                WebSocketSession sendedPushSession = userSessionMap.get(sendedPushUser); // 로그인 상태일 때 알람 보냄

                // 부모 댓글
                if ("reply".equals(pushCategory) && sendedPushSession != null) {
                    TextMessage textMsg = new TextMessage
                    (replyWriter + "님이" + "<a href='/community/read?baordnum=" + boardId +
                    "' style =\"color:balck\"> <strong>" + title + "</strong> 에 댓글을 남겼습니다. </a>" );

                    sendedPushSession.sendMessage(textMsg);
                }
                // 좋아요
                else if ("like".equals(pushCategory) && sendedPushSession != null) {
                    TextMessage textMsg = new TextMessage(replyWriter + "님이" + "<a href='/community/read?baordnum=" + boardId +
                    "' style =\"color:balck\"> <strong>" + title + "</strong> 을 좋아요 했습니다. </a>" );
                }

            } // inner if
        } // outer if
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info ("Socket 연결 해제");
        sessions.remove(session);
        userSessionMap.remove(sendPushUsername(session), session);
    }

    // 알람을 보내는 유저 (댓글작성, 좋아요 누른 쪽)
    private String sendPushUsername(WebSocketSession session) {
        String loginUsername;

        if(session.getPrincipal() == null) {
            loginUsername = null;
        }
        else { // 스프링 시큐리티로 로그인을 구현해서 이래요,,
            loginUsername = session.getPrincipal().getName();
        }

        return loginUsername;
    }
}
