package com.myeon.suda.entity;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Getter
@ToString(exclude = "board")
public class BoardImage extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long board_inum;

    private String board_img_name;

    private String board_path;

    private String board_uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

}