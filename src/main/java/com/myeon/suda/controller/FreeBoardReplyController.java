package com.myeon.suda.controller;

import com.myeon.suda.dto.*;
import com.myeon.suda.service.FreeBoardReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/replies")
@RequiredArgsConstructor
public class FreeBoardReplyController {
    
    private final FreeBoardReplyService reply_service;

   @GetMapping("/pages/{freeBoardNo}/{page}")
    public ResponseEntity<FreeBoardReplyPageDTO> get_list_page(@PathVariable("page")int page, @PathVariable("freeBoardNo") Long freeBoardNo){
        PageRequestDTO requestDTO = new PageRequestDTO(page, 4);
        Pageable pageable = requestDTO.get_pageable(Sort.by("freeBoardRno").descending());
        return new ResponseEntity<FreeBoardReplyPageDTO>(reply_service.get_board_reply_list_page(freeBoardNo, pageable), HttpStatus.OK);
    }

    @PostMapping("/{freeBoardNo}")
    public ResponseEntity<Long> add_review(@RequestBody FreeBoardReplyDTO replyDTO){
        Long freeBoardNo = reply_service.register(replyDTO);
        return new ResponseEntity<Long>(freeBoardNo, HttpStatus.OK);
    }

    @PutMapping("/{freeBoardNo}/{freeBoardRno}")
    public ResponseEntity<Long> modify_review(@PathVariable Long freeBoardRno, @RequestBody FreeBoardReplyDTO replyDTO){
        reply_service.modify(replyDTO);
        return new ResponseEntity<Long>(freeBoardRno, HttpStatus.OK);
    }

    @DeleteMapping("/{freeBoardNo}/{freeBoardRno}")
    public ResponseEntity<Long> remove_review(@PathVariable Long freeBoardRno){
        reply_service.remove(freeBoardRno);
        return new ResponseEntity<Long>(freeBoardRno, HttpStatus.OK);
    }
}