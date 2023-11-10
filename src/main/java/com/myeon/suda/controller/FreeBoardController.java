package com.myeon.suda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myeon.suda.dto.FreeBoardDTO;
import com.myeon.suda.dto.PageRequestDTO;
import com.myeon.suda.service.FreeBoardService;
import com.myeon.suda.service.RamyeonService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequiredArgsConstructor
@Log4j2
public class FreeBoardController {
    private final FreeBoardService board_service;
    
    private final RamyeonService ramyeon_service;

    @GetMapping("/fregister")
    public void register(Model model){
        model.addAttribute("dto5", ramyeon_service.get_main_page_new_review(new PageRequestDTO(1,5)));
        model.addAttribute("dto6", board_service.get_list_community(new PageRequestDTO(1,5)));
    }

    @PostMapping("/fregister")
    public String fregister(FreeBoardDTO boardDTO, RedirectAttributes redirect_attributes){
        if(boardDTO.getBoardCategory() ==1){
            Long freeBoardNo = board_service.register(boardDTO);
            redirect_attributes.addFlashAttribute("msg", freeBoardNo);
            return "redirect:/rlist";
        }else{
            Long freeBoardNo = board_service.register(boardDTO);
            redirect_attributes.addFlashAttribute("msg", freeBoardNo);
            return "redirect:/flist";
        }
    }

    @GetMapping("/flist")
    public void free_list(Model model, PageRequestDTO pageRequestDTO){
        model.addAttribute("dto5", ramyeon_service.get_main_page_new_review(new PageRequestDTO(1,5)));
        model.addAttribute("dto6", board_service.get_list_community(new PageRequestDTO(1,5)));
        if(pageRequestDTO.getKeyword() == null) {
            pageRequestDTO.setKeyword("");
            log.info(pageRequestDTO.getKeyword());
        }
            pageRequestDTO.setSize(20);
        model.addAttribute("result", board_service.get_free_list_page(pageRequestDTO));
    }

    @GetMapping("/rlist")
    public void recipe_list(Model model, PageRequestDTO pageRequestDTO){
        model.addAttribute("dto5", ramyeon_service.get_main_page_new_review(new PageRequestDTO(1,5)));
        model.addAttribute("dto6", board_service.get_list_community(new PageRequestDTO(1,5)));
        if(pageRequestDTO.getKeyword() == null) {
            pageRequestDTO.setKeyword("");
            log.info(pageRequestDTO.getKeyword());
        }
            pageRequestDTO.setSize(20);
        model.addAttribute("result", board_service.get_recipe_list_page(pageRequestDTO));
    }

    @GetMapping({"/fread","/fmodify"})
    public void fread(long freeBoardNo, RedirectAttributes redirect_attributes, Model model, @ModelAttribute("requestDTO") PageRequestDTO requestDTO){
        model.addAttribute("dto5", ramyeon_service.get_main_page_new_review(new PageRequestDTO(1,5)));
        model.addAttribute("dto6", board_service.get_list_community(new PageRequestDTO(1,5)));
        FreeBoardDTO free_boardDTO = board_service.get_board(freeBoardNo);
        model.addAttribute("dto", free_boardDTO);
    }

    @PostMapping("/fmodify")
    public String modify(FreeBoardDTO boardDTO, RedirectAttributes redirectAttributes){
        board_service.modify(boardDTO);
        redirectAttributes.addFlashAttribute("msg","modify");
        redirectAttributes.addAttribute("freeBoardNo",boardDTO.getFreeBoardNo());
        return "redirect:/fread";
    }

    @GetMapping("/remove")
    public String remove(FreeBoardDTO boardDTO, RedirectAttributes redirectAttributes){
        log.info("category"+boardDTO.getBoardCategory());
        redirectAttributes.addFlashAttribute("msg","remove");
        if(boardDTO.getBoardCategory() == 1){
        board_service.remove(boardDTO);
            return  "redirect:/rlist";
        }else{
        board_service.remove(boardDTO);
            return "redirect:/flist";
        }
    }

}
