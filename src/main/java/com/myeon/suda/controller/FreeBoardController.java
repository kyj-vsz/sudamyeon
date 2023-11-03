package com.myeon.suda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.myeon.suda.dto.PageRequestDTO;
import com.myeon.suda.service.RamyeonService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequiredArgsConstructor
@Log4j2
public class FreeBoardController {
    // private final FreeBoardService free_board_service;
    
    private final RamyeonService ramyeon_service;

    @GetMapping("/fregister")
    public void register(Model model){
        model.addAttribute("dto5", ramyeon_service.get_main_page_new_review(new PageRequestDTO(1,5)));
    }

    @GetMapping("/flist")
    public void list(Model model){
        model.addAttribute("dto5", ramyeon_service.get_main_page_new_review(new PageRequestDTO(1,5)));
    }
    
    @GetMapping({"/fread","/fmodify"})
    public void read(Model model){
        model.addAttribute("dto5", ramyeon_service.get_main_page_new_review(new PageRequestDTO(1,5)));
    }

    @GetMapping("/remove")
    public void remove(){}

}
