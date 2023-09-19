package com.team.pretLancer_7.email;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.proxy.annotation.Post;

@Controller
@Slf4j
@RequiredArgsConstructor

public class EmailController {

    private final EmailServiceImpl emailService;

    private static String password;
    
    @GetMapping("email")
    @ResponseBody
    public String sendEmail(@RequestParam String email) throws Exception {
        log.debug("컨트롤러 들어오나요? {}", email);
        int pwdNumber = emailService.sendMail(email);
        String password = "" + pwdNumber;
        
        return password;
    }

    @GetMapping("confirm")
    @ResponseBody
    public String confirmPassword(@RequestParam String sendPassword) throws Exception {
        String result;
    //    password = " " + emailService.getNumber;
        log.debug("인증 컨트롤러에 들어오는 번호는? {}",sendPassword);
        log.debug("스태틱 비밀번호는 ? {}",password);

        if (sendPassword == password) {
            result = "true";
        }
        else {
            result = "false";
        }

        return result;
    }
    
    @PostMapping("qnaemail")
    @ResponseBody
    public String qnaemail(String email, String category, String title, String content, String file) {
    	log.debug("email : {}, category : {}, title : {}, content : {}", email, category, title, content);
    	int qnaEmail = emailService.sendQnaMail(email, category, title, content, file);
    	return "sended message";
    }
}
