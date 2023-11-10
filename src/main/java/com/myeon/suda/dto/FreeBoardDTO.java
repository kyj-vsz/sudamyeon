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
    private Long freeBoardNo;

    private String freeBoardTitle;

    private String freeBoardContent;

    private String writerEmailId;

    private String writerNickname;

    private int replyCount;

    private Long boardCategory;

    private LocalDateTime regdate, moddate;
}
