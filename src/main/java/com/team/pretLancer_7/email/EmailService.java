package com.team.pretLancer_7.email;

public interface EmailService {
    String sendSimpleMessage(String to)throws Exception;
    int sendQnaMail(String email, String category, String title, String content, String file);
}