package com.myeon.suda.dto;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class PageRequestDTO {
    private int page;

    private int size;

    private String type;

    private String keyword;

    private List<String> brand;

    private List<String> cooking;
    
    private List<String> category;

    public PageRequestDTO(){
        this.page = 1;
        this.size = 12;
    }

    public PageRequestDTO(int page, int size){
        this.page = page;
        this.size = size;
    }

    public Pageable get_pageable(Sort sort){
        return PageRequest.of(page-1,size,sort);
    }

       public Pageable get_pageable(){
        return PageRequest.of(page-1,size);
    }
}
