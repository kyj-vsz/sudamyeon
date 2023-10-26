package com.myeon.suda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myeon.suda.dto.MemberDTO;
import com.myeon.suda.dto.RamyeonDTO;
import com.myeon.suda.service.MemberService;
import com.myeon.suda.service.RamyeonService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequestMapping("/members")
@Controller
@RequiredArgsConstructor
@Log4j2
public class RamyeonController {

    private final MemberService member_service;

    private final RamyeonService ramyeon_service;

    @GetMapping("/signup")
    public void signup(){}

    @PostMapping("/signup")
    public String processSignup(MemberDTO memberDTO) {
        // 사용자 입력 데이터가 userDTO 객체로 자동 바인딩됨
        member_service.join(memberDTO); // 회원 가입 로직 호출
        return "redirect:/members/login"; // 가입 후 로그인 페이지로 리다이렉트
    }

    @GetMapping("/login")
    public void login(){

    }

    @GetMapping("/register")
    public void register(){

    }

    @PostMapping("/register")
    public String register(RamyeonDTO ramyeonDTO, RedirectAttributes redirectAttributes){
        log.info("ramyeon-data :"+ ramyeonDTO);
        Long mno = ramyeon_service.register(ramyeonDTO);
        redirectAttributes.addFlashAttribute("msg", mno);
        return "redirect:/members/list";
    }
    
    @GetMapping("/list")
    public void list(){
        
    }

    @GetMapping({"/read","/modify"})
    public void read(long mno, Model model){
        RamyeonDTO ramyeonDTO = ramyeon_service.get_ramyeon(mno);
        model.addAttribute("dto", ramyeonDTO);
    }

    @PostMapping("/modify")
    public String modify(RamyeonDTO ramyeonDTO, RedirectAttributes redirectAttributes){
        ramyeon_service.modify(ramyeonDTO);  
        redirectAttributes.addFlashAttribute("msg", "modify");
        redirectAttributes.addAttribute("mno",ramyeonDTO.getMno());
        return "redirect:/members/read";      
    }

    @GetMapping("/remove")
    public String remove(long mno, RedirectAttributes redirectAttributes){
        ramyeon_service.remove(mno);
        redirectAttributes.addFlashAttribute("msg","remove");
        return "redirect:/members/list";
    }
    
}