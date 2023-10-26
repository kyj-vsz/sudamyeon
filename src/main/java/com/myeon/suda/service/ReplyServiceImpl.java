package com.myeon.suda.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.myeon.suda.dto.ReplyDTO;
import com.myeon.suda.entity.Reply;
import com.myeon.suda.entity.Review;
import com.myeon.suda.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {
    private final ReplyRepository reply_repository;
    
    @Override
    public List<ReplyDTO> get_list(Long rno) {
        Review review = Review.builder().rno(rno).build();
        List<Reply> result = reply_repository.findByReview(review);
        return result.stream().map(list -> to_dto(list))
                        .collect(Collectors.toList());        
    }

    @Override
    public Long register(ReplyDTO replyDTO) {
        Reply reply = to_entity(replyDTO);
        reply_repository.save(reply);
        return reply.getReplyNo();        
    }
    
}
