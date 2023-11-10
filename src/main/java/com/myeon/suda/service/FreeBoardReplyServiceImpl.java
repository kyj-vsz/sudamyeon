package com.myeon.suda.service;

import com.myeon.suda.dto.FreeBoardReplyDTO;
import com.myeon.suda.dto.FreeBoardReplyPageDTO;
import com.myeon.suda.dto.ReviewPageDTO;
import com.myeon.suda.entity.FreeBoard;
import com.myeon.suda.entity.FreeBoardReply;
import com.myeon.suda.repository.FreeBoardReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class FreeBoardReplyServiceImpl implements FreeBoardReplyService {
    private final FreeBoardReplyRepository reply_repository;

    @Override
    public FreeBoardReplyPageDTO get_board_reply_list_page(Long freeBoardNo, Pageable pageable) {
        FreeBoard board = FreeBoard.builder().freeBoardNo(freeBoardNo).build();
        List<FreeBoardReply> result = reply_repository.findByBoard(board, pageable);
        List<FreeBoardReplyDTO> list = result.stream().map(reply -> to_dto(reply))
                .collect(Collectors.toList());
        return new FreeBoardReplyPageDTO(reply_repository.countByBoard(board), list);
    }

    @Override
    public Long register(FreeBoardReplyDTO replyDTO) {
        FreeBoardReply reply = to_entity(replyDTO);
        reply_repository.save(reply);
        return reply.getFreeBoardRno();
    }

    @Override
    public void modify(FreeBoardReplyDTO replyDTO) {
        Optional<FreeBoardReply> result = reply_repository.findById(replyDTO.getFreeBoardRno());
        if(result.isPresent()){
            FreeBoardReply reply = result.get();
            reply.change_free_board_reply_content(replyDTO.getFreeBoardReplyContent());
            reply_repository.save(reply);
        }
    }

    @Override
    public void remove(Long free_board_rno) {
        reply_repository.deleteById(free_board_rno);
    }
}
