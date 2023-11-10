package com.myeon.suda.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.myeon.suda.dto.PageRequestDTO;
import com.myeon.suda.dto.RamyeonDTO;
import com.myeon.suda.service.RamyeonService;
import com.myeon.suda.service.FreeBoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final RamyeonService ramyeon_service;
    private final FreeBoardService board_service;

    @GetMapping(value="/")
    public String main(Model model){               

        model.addAttribute("dto", ramyeon_service.get_main_page_best(new PageRequestDTO(1,9)));

        model.addAttribute("dto2", ramyeon_service.get_main_page_hot(new PageRequestDTO(1,2)));

        model.addAttribute("dto3", ramyeon_service.get_main_page(new PageRequestDTO(1,2)));

        model.addAttribute("dto5", ramyeon_service.get_main_page_new_review(new PageRequestDTO(1,5)));

        model.addAttribute("dto6", board_service.get_list_community(new PageRequestDTO(1,5))); 
        
        return "main";

    }

    @GetMapping("/sudamyeon")
    public void sudamyeon(){}
    

}
