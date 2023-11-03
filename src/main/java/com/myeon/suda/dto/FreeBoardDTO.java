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
public class FreeBoardDTO {
    private Long free_board_no;

    private String free_board_title;

    private String free_board_content;

    private String writer_email_id;

    private String writer_nickname;

    private int reply_count;

    private LocalDateTime regdate, moddate;
}
