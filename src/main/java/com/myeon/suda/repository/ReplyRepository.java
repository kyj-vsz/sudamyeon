package com.myeon.suda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myeon.suda.entity.Reply;
import com.myeon.suda.entity.Review;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> findByReview(Review review);
}
