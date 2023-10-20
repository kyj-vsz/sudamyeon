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

    private Long price;

    private Long weight;

    private Long calorie;

    @Column(length = 20)
    private String category;
    
    @Column(length = 20)
    private String cooking;

    private Long expiry;

    private LocalDateTime birth;

    @Column(nullable = false)
    private double spicy;

    @Column(nullable = false)
    private double cooktime;

    @Column(nullable = false)
    private double soupnum;

    @Column(nullable = false)
    private double noodleSize;

    @Column(nullable = false)
    private double sodum;

    @Column(nullable = false)
    private double carbohydrate;

    @Column(nullable = false)
    private double protein;

    @Column(nullable = false)
    private double sugar;

    @Column(nullable = false)
    private double fat;

    @Column(nullable = false)
    private double saturatedFat;

    @Column(nullable = false)
    private double transFat;

    @Column(nullable = false)
    private double cholesterol;

    @Column(nullable = false)
    private String etc;

    public void change_content(String content){
        this.content = content;
    }

}
