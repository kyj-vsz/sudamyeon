package com.myeon.suda.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myeon.suda.dto.ReplyDTO;
import com.myeon.suda.service.ReplyService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/replies")
public class ReplyController {
    private final ReplyService reply_service;

    @GetMapping("/{rno}/all")
    public ResponseEntity<List<ReplyDTO>> get_list(@PathVariable("rno")Long rno){
        List<ReplyDTO> replyDTO_list = reply_service.get_list(rno);
        return new ResponseEntity<List<ReplyDTO>>(replyDTO_list, HttpStatus.OK);
    }

    @PostMapping("/{rno}")
    public ResponseEntity<Long> add_reply(@RequestBody ReplyDTO replyDTO){
        Long reply_no = reply_service.register(replyDTO);
        return new ResponseEntity<Long>(reply_no, HttpStatus.OK);
    }
}
