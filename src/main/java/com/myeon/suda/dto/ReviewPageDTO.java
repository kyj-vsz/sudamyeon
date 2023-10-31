package com.myeon.suda.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ReviewPageDTO {
    private int reply_count;
    private List<ReviewDTO> list;
}
