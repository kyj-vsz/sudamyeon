package com.myeon.suda.entity;

import javax.persistence.Column;
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
@Getter
@ToString(exclude = "writer")
public class Board extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    @Column(length = 50, nullable = false)
    private String board_title;

    @Column(length = 2000, nullable = false)
    private String board_content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member writer;

    public void change_board_title(String board_title){
        this.board_title = board_title;
    }

    public void change_board_content(String board_content){
        this.board_content = board_content;
    }
}
