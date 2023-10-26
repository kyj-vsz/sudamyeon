package com.myeon.suda.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Ramyeon extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mno;

    @Column(length = 50)
    private String gname;

    @Column(length = 2000)
    private String content;

    @Column(length = 20)
    private String brand;

    private String price;

    private Long weight;

    private Long calorie;

    @Column(length = 20)
    private String category;
    
    @Column(length = 20)
    private String cooking;

    private String expiry;

    private String birth;

    @Column(nullable = false)
    private Long spicy;

    @Column(nullable = false)
    private String cooktime;

    @Column(nullable = false)
    private Long soupnum;

    @Column(nullable = false)
    private Long noodleSize;

    @Column(nullable = false)
    private Long sodum;

    @Column(nullable = false)
    private Long carbohydrate;

    @Column(nullable = false)
    private Long protein;

    @Column(nullable = false)
    private Long sugar;

    @Column(nullable = false)
    private Long fat;

    @Column(nullable = false)
    private Long saturatedFat;

    @Column(nullable = false)
    private Long transFat;

    @Column(nullable = false)
    private Long cholesterol;

    @Column(nullable = false)
    private String etc;

    public void change_content(String content){
        this.content = content;
    }

}
