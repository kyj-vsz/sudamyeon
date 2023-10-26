package com.myeon.suda.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RamyeonDTO {
    private Long mno;

    private String gname;

    private String content;

    private String brand;

    private String price;

    private Long weight;

    private Long calorie;

    private String category;

    private String cooking;

    private String expiry;

    private String birth;

    private Long spicy;

    private String cooktime;

    private Long soupnum;

    private Long noodle_size;
    
    private Long sodum;
    
    private Long carbohydrate;
    
    private Long protein;
    
    private Long sugar;
    
    private Long fat;
    
    private Long saturated_fat;
    
    private Long trans_fat;
    
    private Long cholesterol;

    private String etc;
    
    @Builder.Default
    private List<ImageDTO> imageDTO_list = new ArrayList<ImageDTO>();
    
    private double avg;

    private int review_count;

    private LocalDateTime regdate, moddate;
}
