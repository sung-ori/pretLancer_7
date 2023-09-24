package com.team.pretLancer_7.email;

import java.time.LocalDate;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.team.pretLancer_7.domain.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
 
@Service
@Slf4j
@RequiredArgsConstructor
public class EmailServiceImpl {

    private final JavaMailSender javaMailSender;
    private static final String senderEmail= "projectSeven0305@gmail.com";
    private static int number;
    
    public static void createNumber(){
        number = (int)(Math.random() * (90000)) + 100000;// (int) Math.random() * (최댓값-최소값+1) + 최소값
    }

    public MimeMessage CreateMail(String email){
        log.debug("서비스도 들어오니?CM : {}", email);
        createNumber();
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            message.setFrom(senderEmail);
            message.setRecipients(MimeMessage.RecipientType.TO, email);
            message.setSubject("이메일 인증");
            String body = "";
            body += "<h3>" + "요청하신 인증 번호입니다." + "</h3>";
            body += "<h1>" + number + "</h1>";
            body += "<h3>" + "감사합니다." + "</h3>";
            message.setText(body,"UTF-8", "html");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return message;
    }

    public int sendMail(String email){
        log.debug("서비스도 들어오니? : {}", email);
        MimeMessage message = CreateMail(email);
        javaMailSender.send(message);
        return number;
    }

    //qna 이메일 보내기
	public int sendQnaMail(String email, String category, String title, String content, String file) {
        MimeMessage message = javaMailSender.createMimeMessage();
        String address = "pretlancer.qna@gmail.com";

        try {
            message.setFrom(senderEmail);
            message.setRecipients(MimeMessage.RecipientType.TO, address);
            message.setSubject("[" + category + "] " + title);
            String body = "적어주신 고객님 이메일 : " + email + "\n" + "내용 : " + content;
            message.setText(body,"UTF-8", "html");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return 0;
	}

    public int sendPayEmail(Member userinfo, int cash) {

        MimeMessage message = CreatePayMail(userinfo,cash);
        javaMailSender.send(message);

        return 1;
    }

    public MimeMessage CreatePayMail(Member userinfo, int cash) {
        String email = userinfo.getMembermail();
        String userid = userinfo.getMemberid();

        log.debug("서비스도 들어오니?CM : {}", email);
        // 오늘 날짜
        LocalDate today = LocalDate.now();
        
        // 7일 뒤 날짜 계산
        LocalDate sevenDaysLater = today.plusDays(7);

        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            message.setFrom(senderEmail);
            message.setRecipients(MimeMessage.RecipientType.TO, email);
            message.setSubject("출금 확인 메일");
            String body = "";

            body += "<h3>" + userid + "님!" + "</h3>";
            body += "<h3>" + "출금 신청이 완료되엇습니다." + "</h3>";
            body += "<h3>" + "신청하신 금액" + cash +"원은 "+ "</h3>";
            body += "<h1>" + sevenDaysLater +"안에 입금됩니다."+"</h1>";
            body += "<h3>" + "감사합니다." + "</h3>";
            message.setText(body,"UTF-8", "html");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return message;
    }
}