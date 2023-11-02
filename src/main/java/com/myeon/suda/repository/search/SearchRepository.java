package com.myeon.suda.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.myeon.suda.dto.PageRequestDTO;

public interface SearchRepository {
    
    Page<Object[]> search_page(PageRequestDTO pageRequestDTO, Pageable pageable);
}
