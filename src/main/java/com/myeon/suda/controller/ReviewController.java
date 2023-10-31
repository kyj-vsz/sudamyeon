package com.myeon.suda.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myeon.suda.dto.PageRequestDTO;
import com.myeon.suda.dto.ReviewDTO;
import com.myeon.suda.service.ReviewService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {
    
    private final ReviewService review_service;

   @GetMapping("/pages/{mno}/{page}")
    public ResponseEntity<ReviewPageDTO> get_list_page(@PathVariable("page")int page, @PathVariable("mno") Long mno){
        PageRequestDTO requestDTO = new PageRequestDTO(page, 4);
        Pageable pageable = requestDTO.get_pageable();
        return new ResponseEntity<ReviewPageDTO>(review_service.get_review_list_page(mno, pageable), HttpStatus.OK);
    }

    @PostMapping("/{mno}")
    public ResponseEntity<Long> add_review(@RequestBody ReviewDTO reviewDTO){
        Long rno = review_service.register(reviewDTO);
        return new ResponseEntity<Long>(rno, HttpStatus.OK);
    }

    @PutMapping("/{mno}/{rno}")
    public ResponseEntity<Long> modify_review(@PathVariable Long rno, @RequestBody ReviewDTO reviewDTO){
        review_service.modify(reviewDTO);
        return new ResponseEntity<Long>(rno, HttpStatus.OK);
    }

    @DeleteMapping("/{mno}/{rno}")
    public ResponseEntity<Long> remove_review(@PathVariable Long rno){
        review_service.remove(rno);
        return new ResponseEntity<Long>(rno, HttpStatus.OK);
    }
}