package com.myeon.suda.service;

import java.util.List;

import com.myeon.suda.dto.ReviewDTO;
import com.myeon.suda.entity.Member;
import com.myeon.suda.entity.Ramyeon;
import com.myeon.suda.entity.Review;

public interface ReviewService {
    // PageResultDTO<ReviewDTO, Object[]> get_review_list_page(PageRequestDTO requestDTO);
    List<ReviewDTO> get_list(Long mno);

    Long register(ReviewDTO reviewDTO);

    void modify(ReviewDTO reviewDTO);

    void remove(Long rno);

    default Review to_entity(ReviewDTO reviewDTO){
        Review review = Review.builder()
                .rno(reviewDTO.getRno())
                .reviewContent(reviewDTO.getReview_content())
                .grade(reviewDTO.getGrade())
                .ramyeon(Ramyeon.builder().mno(reviewDTO.getMno()).build())
                .member(Member.builder().emailId(reviewDTO.getEmail_id()).build())
                .build();
        return review;
    }

    default ReviewDTO to_dto(Review review){
        ReviewDTO reviewDTO = ReviewDTO.builder()
                .rno(review.getRno())
                .mno(review.getRamyeon().getMno())
                .email_id(review.getMember().getEmailId())
                .nickname(review.getMember().getNickname())
                .review_content(review.getReviewContent())
                .grade(review.getGrade())
                .regdate(review.getRegDate())
                .moddate(review.getModDate())
                .build();
        return reviewDTO;
    }
}
