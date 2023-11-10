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
public class FreeBoard extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long freeBoardNo;

    @Column(length = 50, nullable = false)
    private String freeBoardTitle;

    @Column(length = 5000, nullable = false)
    private String freeBoardContent;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member writer;

    @Column(length = 1, nullable = false)
    private Long boardCategory;

    public void change_board_title(String freeBoardTitle){
        this.freeBoardTitle= freeBoardTitle;
    }

    public void change_board_content(String freeBoardContent){
        this.freeBoardContent = freeBoardContent;
    }
}
