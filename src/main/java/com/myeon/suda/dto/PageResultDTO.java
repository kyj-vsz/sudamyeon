package com.myeon.suda.dto;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lombok.Data;

@Data
public class PageResultDTO<DTO, EN> {
    private List<DTO> dto_list;

    private int total_page;

    private int page;

    private int size;

    private int start, end;

    private boolean prev, next;

    private List<Integer> page_list;

    public PageResultDTO(Page<EN> result, Function<EN, DTO> fn){
        dto_list = result.stream().map(fn).collect(Collectors.toList());
        total_page = result.getTotalPages();
        make_page_list(result.getPageable());
    }

    private void make_page_list(Pageable pageable){
        this.page = pageable.getPageNumber() + 1;
        this.size = pageable.getPageSize();
        int temp_end = (int)(Math.ceil(page/10.0)) * 10;
        start = temp_end - 9;
        prev = start > 1;
        end = total_page > temp_end ? temp_end : total_page;
        next = total_page > temp_end;
        page_list = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());

    }
}
