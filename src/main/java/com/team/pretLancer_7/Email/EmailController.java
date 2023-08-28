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

    
    @GetMapping("email")
    @ResponseBody
    public String emailConfirm(@RequestParam String email) throws Exception {
        log.debug("컨트롤러 들어오나요? {}", email);
        int pwdNumber = emailService.sendMail(email);
        String password = "" + pwdNumber;
        
        return password;
    }
}
