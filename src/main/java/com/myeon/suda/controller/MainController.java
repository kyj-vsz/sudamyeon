package com.myeon.suda.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.myeon.suda.dto.RamyeonDTO;
import com.myeon.suda.service.RamyeonService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final RamyeonService ramyeon_service;

    @GetMapping(value="/")
    public String main(Model model){

        RamyeonDTO ramyeonDTO = ramyeon_service.get_img();
        System.out.println("dto"+ ramyeonDTO);
        model.addAttribute("dto", ramyeonDTO);
        
        return "main";
    }
    

}
