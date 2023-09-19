package com.team.pretLancer_7.messaging;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.team.pretLancer_7.domain.Message;

@Mapper
public interface MessagingDAO {
    // 액션이 방생하면 서비스로부터 메세지를 작성하고 DB에 삽입한다.
    public int insertMessage(Message msg) ;
    // DB에 저장된 메세지 중 클릭하지 않은 것만 불러온다.
    // 서비스에서 확인 안한 메세지의 개수도 체크한다. 
    public List<Message> selectUnclickedMessage(String userid);
    // 혹시 몰라서 만든 메세지 클릭하면 없애는 메세지
    public int deleteMessage(int messagenum);
    // 알림 목록을 확인하면 체크가 되고 그 갯수는 화면에 표시하지 않음.
    public int updateCheck();
    // 클릭 해서 확인한 메세지의 처리
    public int updateClick();

}
