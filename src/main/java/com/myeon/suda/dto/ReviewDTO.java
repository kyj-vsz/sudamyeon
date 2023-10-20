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
public class ReviewDTO {
    private Long rno;

    private Long mno;

    private String email_id;

    private String nickname;

    private String review_content;

    private int grade;
    
    private LocalDateTime regdate, moddate;
}
