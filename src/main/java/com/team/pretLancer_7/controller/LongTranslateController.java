package com.team.pretLancer_7.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.team.pretLancer_7.domain.AuctionTranslator;
import com.team.pretLancer_7.domain.MyPage;
import com.team.pretLancer_7.domain.Request_L;
import com.team.pretLancer_7.service.LongService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/long")
public class LongTranslateController {
    
    @Autowired
    LongService service;

    

    @GetMapping("/main")
    public String mainForm(){
        return "translate_long/mainForm";
    }

    @GetMapping("/request")
    public String requestForm(Model model) {
        List<MyPage> translatorList = service.getTranslatorList();
        log.error("돌아오나요? {}", translatorList);
        model.addAttribute("translatorList", translatorList);

        return "translate_long/translatorList";
    }

    @GetMapping("/translatorProfile")
    public String translatorProfile(Model model, @RequestParam(name="memberid") String memberid, @AuthenticationPrincipal UserDetails user ) {
        String loginId = user.getUsername();
        MyPage translatorProfile = service.getOneMyPage(memberid);

        model.addAttribute("tp", translatorProfile);
        model.addAttribute("loginId", loginId);


        return "/translate_long/translatorProfile";
    }

    @GetMapping("/writeRequest")
    public String request(Model model, @RequestParam(name="memberid") String memberid, @AuthenticationPrincipal UserDetails user) {
        String translatorId = memberid;
        String loginId = user.getUsername();
        
        model.addAttribute("translatorId", translatorId);
        model.addAttribute("loginId", loginId);


        return "/translate_long/writeRequestForm";
    }

    @PostMapping("/writeRequest")
    public String writeRequest(Request_L request_L, MultipartFile uploadFile, @AuthenticationPrincipal UserDetails user){
        // String originFileName = uploadFile.getOriginalFilename();
        log.debug("장문 요청 컨트롤러 {}",request_L);
        // log.debug("오리진 파일 {}", originFileName);

        request_L.setMemberid(user.getUsername());

        service.writeRequest(request_L,uploadFile);
        return "redirect:/";
    }

    @GetMapping("/auction")
    public String auctionForm() {
        return "/translate_long/writeAuctionForm";
    }

    @PostMapping("/writeAuction")
    public String writeAuction(Request_L request_L, MultipartFile uploadFile, @AuthenticationPrincipal UserDetails user) {

        request_L.setMemberid(user.getUsername());

        service.writeAuction(request_L,uploadFile);

        return "redirect:/";
    }

    @GetMapping("auctionList")
    public String auctionList(Model model) {
        List<Request_L> auctionList =  service.getAuctionList();
        
        model.addAttribute("auctionList", auctionList);
        return "/translate_long/auctionListForm";
    }
    
    @GetMapping("readAuctionInfo")
    public String readAuctionInfo(Model model, @RequestParam(name="requestnum_l") int requestnum_l) {
        Request_L rql = service.readAuctionInfo(requestnum_l);
        model.addAttribute("info", rql);

        return "/translate_long/auctionInfo";
    }

    @GetMapping("readAuctionPrice")
    @ResponseBody
    public List<AuctionTranslator> readAuctionPrice(@RequestParam(name="requestnum_l") int requestnum_l) {
        List<AuctionTranslator> list ;
        list = service.readAuctionPrice(requestnum_l);

        return list;
    }
}
