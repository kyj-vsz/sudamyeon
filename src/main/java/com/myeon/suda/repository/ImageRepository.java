package com.myeon.suda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myeon.suda.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
    
}
