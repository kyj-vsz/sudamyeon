package com.myeon.suda.service;

import java.util.List;

import com.myeon.suda.dto.ReplyDTO;
import com.myeon.suda.entity.Reply;
import com.myeon.suda.entity.Review;

public interface ReplyService {
    List<ReplyDTO> get_list(Long rno);

    Long register(ReplyDTO replyDTO);

    default Reply to_entity(ReplyDTO replyDTO){
        Reply reply = Reply.builder()
                .replyNo(replyDTO.getReply_no())
                .replyer(replyDTO.getReplyer())
                .replyContent(replyDTO.getReply_content())
                .review(Review.builder().rno(replyDTO.getRno()).build())
                .build();
        return reply;
    }

    default ReplyDTO to_dto(Reply reply){
        ReplyDTO replyDTO = ReplyDTO.builder()
                .reply_no(reply.getReplyNo())
                .rno(reply.getReview().getRno())
                .replyer(reply.getReplyer())
                .reply_content(reply.getReplyContent())
                .regdate(reply.getRegDate())
                .moddate(reply.getModDate())
                .build();
        return replyDTO;
    }
}
