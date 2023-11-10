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
@ToString(exclude = {"board", "member"})
public class FreeBoardReply extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long freeBoardRno;

    @Column(length = 200, nullable = false)
    private String freeBoardReplyContent;

    @ManyToOne(fetch = FetchType.LAZY)
    private FreeBoard board;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    public void change_free_board_reply_content(String freeBoardReplyContent){
        this.freeBoardReplyContent = freeBoardReplyContent;
    }
}