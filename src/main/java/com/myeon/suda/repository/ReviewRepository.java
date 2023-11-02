package com.myeon.suda.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.myeon.suda.entity.Ramyeon;
import com.myeon.suda.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @EntityGraph(attributePaths = {"member"}, type = EntityGraph.EntityGraphType.FETCH)
    List<Review> findByRamyeon(Ramyeon ramyeon);

    @EntityGraph(attributePaths = {"member"}, type = EntityGraph.EntityGraphType.FETCH)
    List<Review> findByRamyeon(Ramyeon ramyeon, Pageable pageable);
    
    @Query(countQuery = "SELECT COUNT(r.ramyeon) FROM Review r WHERE r.ramyeon = :ramyeon")
    int countByRamyeon(Ramyeon ramyeon);
}
