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
public class ReplyDTO {
    private Long reply_no;

    private String replyer;

    private String reply_content;

    private LocalDateTime regdate, moddate;
}
