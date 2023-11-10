package com.myeon.suda.service;

import com.myeon.suda.dto.FreeBoardDTO;
import com.myeon.suda.dto.PageRequestDTO;
import com.myeon.suda.dto.PageResultDTO;
import com.myeon.suda.entity.FreeBoard;
import com.myeon.suda.entity.Member;
import com.myeon.suda.repository.FreeBoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Log4j2
public class FreeBoardServiceImpl implements FreeBoardService {
    private final FreeBoardRepository board_repository;
    
    @Transactional
    @Override
    public Long register(FreeBoardDTO free_boardDTO) {
        Map<String, Object> entity_map = to_entity(free_boardDTO);
        FreeBoard free_board = (FreeBoard) entity_map.get("free_board");
        board_repository.save(free_board);
        return free_board.getFreeBoardNo();
    }

    @Override
    public PageResultDTO<FreeBoardDTO, Object[]> get_free_list_page(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.get_pageable(Sort.by("freeBoardNo").descending());
        log.info(requestDTO.getKeyword());
        Page<Object[]> result = board_repository.get_free_board_list(pageable, requestDTO.getKeyword());
        Function<Object[], FreeBoardDTO> fn = (arr ->to_dto(
            (FreeBoard) arr[0],
            (Member)arr[1],
            (Long)arr[2]
        ));    
        return new PageResultDTO<>(result, fn);
    }

    @Override
    public PageResultDTO<FreeBoardDTO, Object[]> get_recipe_list_page(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.get_pageable(Sort.by("freeBoardNo").descending());
        log.info(requestDTO.getKeyword());
        Page<Object[]> result = board_repository.get_recipe_board_list(pageable, requestDTO.getKeyword());
        Function<Object[], FreeBoardDTO> fn = (arr ->to_dto(
            (FreeBoard) arr[0],
            (Member)arr[1],
            (Long)arr[2]
        ));
        return new PageResultDTO<>(result, fn);
    }

    @Override
    public FreeBoardDTO get_board(Long freeBoardNo) {
        List<Object[]> result = board_repository.get_board(freeBoardNo);
        FreeBoard free_board = (FreeBoard) result.get(0)[0];
        Member writer = (Member)result.get(0)[1];
        Long reply_count = (Long)result.get(0)[2];
        return to_dto(free_board, writer, reply_count);
    }

    @Override
    public void remove(FreeBoardDTO boardDTO) {
        board_repository.delete_board(boardDTO.getFreeBoardNo(), boardDTO.getBoardCategory());
    }

    @Override
    public void modify(FreeBoardDTO boardDTO){
        Optional<FreeBoard> result = board_repository.findById(boardDTO.getFreeBoardNo());
        if(result.isPresent()){
            FreeBoard free_board = result.get();
            free_board.change_board_title(boardDTO.getFreeBoardTitle());
            free_board.change_board_content(boardDTO.getFreeBoardContent());
            board_repository.save(free_board);
        }
    }

    @Override
    public PageResultDTO<FreeBoardDTO, Object[]> get_list_community(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.get_pageable(Sort.by("freeBoardNo").descending());
        Page<Object[]> result = board_repository.get_list_community(pageable);
        Function<Object[], FreeBoardDTO> fn = (arr ->to_dto(
                (FreeBoard) arr[0],
                (Member)arr[1],
                (Long)arr[2]
        ));
        return new PageResultDTO<>(result, fn);
    }
}
