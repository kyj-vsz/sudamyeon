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

    private Long price;

    private Long weight;

    private Long calorie;

    private String category;

    private String cooking;

    private Long expiry;

    private LocalDateTime birth;

    private double spicy;

    private double cooktime;

    private double soupnum;

    private double noodle_size;
    
    private double sodum;
    
    private double carbohydrate;
    
    private double protein;
    
    private double sugar;
    
    private double fat;
    
    private double saturated_fat;
    
    private double trans_fat;
    
    private double cholesterol;
    
    @Builder.Default
    private List<ImageDTO> imageDTO_list = new ArrayList<ImageDTO>();
    
    private double avg;

    private int review_count;

    private LocalDateTime regdate, moddate;
}
