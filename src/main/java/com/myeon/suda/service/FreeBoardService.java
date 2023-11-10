package com.myeon.suda.service;

import java.util.HashMap;
import java.util.Map;

import com.myeon.suda.dto.FreeBoardDTO;
import com.myeon.suda.dto.PageRequestDTO;
import com.myeon.suda.dto.PageResultDTO;
import com.myeon.suda.entity.FreeBoard;
import com.myeon.suda.entity.Member;

public interface FreeBoardService {
    Long register(FreeBoardDTO free_boardDTO);

    PageResultDTO<FreeBoardDTO, Object[]> get_free_list_page(PageRequestDTO requestDTO);

    PageResultDTO<FreeBoardDTO, Object[]> get_recipe_list_page(PageRequestDTO requestDTO);

    FreeBoardDTO get_board(Long freeBoardNo);

    void remove(FreeBoardDTO boardDTO);

    void modify(FreeBoardDTO boardDTO);

    PageResultDTO<FreeBoardDTO, Object[]> get_list_community(PageRequestDTO requestDTO);


    
    default Map<String, Object> to_entity(FreeBoardDTO free_boardDTO){
        Map<String, Object> entity_map = new HashMap<>();

        Member member = Member.builder()
                .emailId(free_boardDTO.getWriterEmailId())
                .build();

        FreeBoard free_board = FreeBoard.builder()
                .freeBoardNo(free_boardDTO.getFreeBoardNo())
                .freeBoardTitle(free_boardDTO.getFreeBoardTitle())
                .freeBoardContent(free_boardDTO.getFreeBoardContent())
                .writer(member)
                .boardCategory(free_boardDTO.getBoardCategory())
                .build();

        entity_map.put("free_board", free_board);
        
        return entity_map;
    }

    default FreeBoardDTO to_dto(FreeBoard free_board, Member writer, Long reply_count){

        FreeBoardDTO free_boardDTO = FreeBoardDTO.builder()
                .freeBoardNo(free_board.getFreeBoardNo())
                .freeBoardTitle(free_board.getFreeBoardTitle())
                .freeBoardContent(free_board.getFreeBoardContent())
                .writerEmailId(writer.getEmailId())
                .writerNickname(writer.getNickname())
                .replyCount(reply_count.intValue())
                .regdate(free_board.getRegDate())
                .boardCategory(free_board.getBoardCategory())
                .moddate(free_board.getModDate())
                .build();

        return free_boardDTO;
    }
}
