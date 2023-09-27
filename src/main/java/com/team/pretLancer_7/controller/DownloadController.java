package com.team.pretLancer_7.controller;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DownloadController {

    private static final String FILE_DIRECTORY_R = "/Users/sung_ori/pretLancer_7/pretLancer_7/src/main/resources/static/request"; // 실제 파일이 저장된 디렉터리 경로
    private static final String FILE_DIRECTORY_T = "/Users/sung_ori/pretLancer_7/pretLancer_7/src/main/resources/static/translate"; // 실제 파일이 저장된 디렉터리 경로

    @GetMapping("/downloadR")
    @ResponseBody
    public ResponseEntity<Resource> downloadFileR(@RequestParam("fileName") String fileName) throws MalformedURLException {
        // 실제 파일 경로 구성
        Path filePath = Paths.get(FILE_DIRECTORY_R).resolve(fileName);

        try {
            // 파일을 로드하고 리소스로 변환
            Resource resource = new UrlResource(filePath.toUri());
    
            // 원래 파일 이름에서 확장자 추출
            String originalFileName = resource.getFilename();
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
    
            // 다운로드를 위한 Response 설정
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + originalFileName);
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
    
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(resource);
        } catch (IOException e) {
            // 파일을 찾을 수 없거나 다운로드할 수 없는 경우 예외 처리
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/downloadT")
    @ResponseBody
    public ResponseEntity<Resource> downloadFileT(@RequestParam("fileName") String fileName) throws MalformedURLException {
        // 실제 파일 경로 구성
        Path filePath = Paths.get(FILE_DIRECTORY_T).resolve(fileName);


    try {
        // 파일을 로드하고 리소스로 변환
        Resource resource = new UrlResource(filePath.toUri());

        // 원래 파일 이름에서 확장자 추출
        String originalFileName = resource.getFilename();
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));

        // 다운로드를 위한 Response 설정
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + originalFileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    } catch (IOException e) {
        // 파일을 찾을 수 없거나 다운로드할 수 없는 경우 예외 처리
        e.printStackTrace();
        return ResponseEntity.notFound().build();
    }
    }
}
