package com.myeon.suda.service;

import com.myeon.suda.dto.FreeBoardReplyDTO;
import com.myeon.suda.dto.FreeBoardReplyPageDTO;
import com.myeon.suda.entity.FreeBoard;
import com.myeon.suda.entity.FreeBoardReply;
import com.myeon.suda.entity.Member;
import org.springframework.data.domain.Pageable;

public interface FreeBoardReplyService {
    FreeBoardReplyPageDTO get_board_reply_list_page(Long free_board_no, Pageable pageable);

    Long register(FreeBoardReplyDTO replyDTO);

    void modify(FreeBoardReplyDTO replyDTO);

    void remove(Long free_board_rno);

    default FreeBoardReply to_entity(FreeBoardReplyDTO replyDTO){
        FreeBoardReply reply = FreeBoardReply.builder()
                .freeBoardRno(replyDTO.getFreeBoardRno())
                .freeBoardReplyContent(replyDTO.getFreeBoardReplyContent())
                .board(FreeBoard.builder().freeBoardNo(replyDTO.getFreeBoardNo()).build())
                .member(Member.builder().emailId(replyDTO.getEmail_id()).build())
                .build();
        return reply;
    }

    default FreeBoardReplyDTO to_dto(FreeBoardReply reply){
        FreeBoardReplyDTO replyDTO = FreeBoardReplyDTO.builder()
                .freeBoardRno(reply.getFreeBoardRno())
                .freeBoardNo(reply.getBoard().getFreeBoardNo())
                .email_id(reply.getMember().getEmailId())
                .nickname(reply.getMember().getNickname())
                .freeBoardReplyContent(reply.getFreeBoardReplyContent())
                .regdate(reply.getRegDate())
                .moddate(reply.getModDate())
                .build();
        return replyDTO;
    }
}
