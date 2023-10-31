package com.myeon.suda.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.myeon.suda.dto.ReviewDTO;
import com.myeon.suda.dto.ReviewPageDTO;
import com.myeon.suda.entity.Ramyeon;
import com.myeon.suda.entity.Review;
import com.myeon.suda.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{
    private final ReviewRepository review_repository;

    
    @Override
    public ReviewPageDTO get_review_list_page(Long mno, Pageable pageable) {
        Ramyeon ramyeon = Ramyeon.builder().mno(mno).build();
        List<Review> result = review_repository.findByRamyeon(ramyeon, pageable);
        List<ReviewDTO> list = result.stream().map(review -> to_dto(review)).collect(Collectors.toList());
        return new ReviewPageDTO(review_repository.countByRamyeon(ramyeon),list);
    }

    @Override
    public List<ReviewDTO> get_list(Long mno) {
        Ramyeon ramyeon = Ramyeon.builder().mno(mno).build();
        List<Review> result = review_repository.findByRamyeon(ramyeon);
        return result.stream().map(review -> to_dto(review)).collect(Collectors.toList());
    }

    @Override
    public Long register(ReviewDTO reviewDTO) {
        Review review = to_entity(reviewDTO);
        review_repository.save(review);
        return review.getRno();
    }

    @Override
    public void modify(ReviewDTO reviewDTO) {
        Optional<Review> result = review_repository.findById(reviewDTO.getRno());
        if(result.isPresent()){
            Review review = result.get();
            review.changeGrade(reviewDTO.getGrade());
            review.changeText(reviewDTO.getReview_content());
            review_repository.save(review);
        }
    }

    @Override
    public void remove(Long rno) {
       review_repository.deleteById(rno);
    }

}
