package com.team.pretLancer_7.Email;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
}
