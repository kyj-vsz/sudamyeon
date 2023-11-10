package com.myeon.suda.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myeon.suda.service.RamyeonService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {
    
    private final RamyeonService ramyeon_service;

    @DeleteMapping("/{mno}/{inum}")
    public ResponseEntity<Long> remove_image(@PathVariable Long inum){
        ramyeon_service.remove_image(inum);
        return new ResponseEntity<Long>(inum, HttpStatus.OK);
    }
}
