package com.team.pretLancer_7.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.team.pretLancer_7.dao.LongDAO;
import com.team.pretLancer_7.domain.MyPage;
import com.team.pretLancer_7.domain.Request_L;
import com.team.pretLancer_7.utill.FileService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class LongServiceImpl implements LongService{

    @Value("${spring.servlet.multipart.location}")
	String uploadPath;

    @Autowired
    LongDAO dao ;

    @Override
    public List<MyPage> getTranslatorList() {
        List<MyPage> translatorList = new ArrayList();

        translatorList =  dao.selectAdTranslator();
        log.error("서비스는 돌아오나? {}", translatorList);
        return translatorList;
    }

    @Override
    public MyPage getOneMyPage(String memberid) {
        return dao.selectOneMyPage(memberid);
    }

    @Override
    public int writeRequest(Request_L request_L, MultipartFile uploadFile) {
        FileService fileService = new FileService();
        log.debug("장문 요청 서비스 {}",request_L);
        fileService.saveFile(uploadFile,uploadPath);
        String originfile =""; 
        try{

            originfile = uploadFile.getOriginalFilename();
        }
        catch (NullPointerException e) {
            e.setStackTrace(null);
        }
        
        request_L.setOriginrfile(originfile);
        return dao.insertOneRequst(request_L);

    }
    
}
