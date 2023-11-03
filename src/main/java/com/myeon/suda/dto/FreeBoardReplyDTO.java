package com.myeon.suda.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FreeBoardReplyDTO {
    private Long free_board_rno;

    private String free_board_replyer;

    private String free_board_reply_content;

    private LocalDateTime regdate, moddate;
}
