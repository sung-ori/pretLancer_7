package com.team.pretLancer_7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.team.pretLancer_7.domain.MyPage;
import com.team.pretLancer_7.service.MypageService;
import com.team.pretLancer_7.utill.FileService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("my_page")
public class MypageController {
	
	@Value("${spring.servlet.multipart.location}")
	String uploadPath;

    @Autowired
    MypageService service;

    @GetMapping("main")
    public String myPageForm(Model m, UserDetails user) {
        
        MyPage mp =  service.getMyPage(user.getUsername());
        m.addAttribute("MyPage", mp);
        
        return "/myPageView/MyPage";
    }
    
    @PostMapping("update")
    public String update(MyPage mP, MultipartFile upload) {
    	
    	// 프로필 사진 저장
    	if (upload != null && !upload.isEmpty()) {
    		String savedfile = FileService.saveFile(upload, uploadPath);
    		mP.setOriginphoto(upload.getOriginalFilename());
    		mP.setSavedphoto(savedfile);
    	}
    	
    	return "/myPageView/MyPage";
    }

}
