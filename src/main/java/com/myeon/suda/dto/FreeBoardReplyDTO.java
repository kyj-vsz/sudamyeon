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
    private Long freeBoardRno;

    private Long freeBoardNo;

    private String email_id;

    private String nickname;

    private String freeBoardReplyContent;

    private LocalDateTime regdate, moddate;
}
