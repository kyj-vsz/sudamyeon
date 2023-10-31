package com.myeon.suda.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myeon.suda.dto.PageRequestDTO;
import com.myeon.suda.dto.PageResultDTO;
import com.myeon.suda.dto.RamyeonDTO;
import com.myeon.suda.entity.Image;
import com.myeon.suda.entity.Ramyeon;
import com.myeon.suda.entity.Review;
import com.myeon.suda.repository.ImageRepository;
import com.myeon.suda.repository.RamyeonRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class RamyeonServiceImpl implements RamyeonService {
    private final RamyeonRepository ramyeon_repository;
    private final ImageRepository image_repository;

    @Transactional
    @Override  
    public Long register(RamyeonDTO ramyeonDTO) {
        Map<String, Object> entity_map = to_entity(ramyeonDTO);
        Ramyeon ramyeon = (Ramyeon) entity_map.get("ramyeon");
        List<Image> image_list = (List<Image>) entity_map.get("img_list");
        ramyeon_repository.save(ramyeon);
        image_list.forEach(image ->{
            image_repository.save(image);
        });
        return ramyeon.getMno();
    }

    @Override
    public PageResultDTO<RamyeonDTO, Object[]> get_list_page(PageRequestDTO requestDTO){
        Pageable pageable = requestDTO.get_pageable(Sort.by("mno").descending());
        Page<Object[]> result = ramyeon_repository.get_list(pageable);
        Function<Object[], RamyeonDTO> fn = (arr -> to_dto(
            (Ramyeon)arr[0], 
            (List<Image>)(Arrays.asList((Image)arr[1])),
            (Double)arr[2],
            (Long)arr[3]
            ));
        return new PageResultDTO<>(result, fn);
    }
    
    @Override
    public RamyeonDTO get_ramyeon(Long mno) {
        List<Object[]> result = ramyeon_repository.get_ramyeon_with_all(mno);
        Ramyeon ramyeon =(Ramyeon) result.get(0)[0]; //<-문제
        List<Image> image_list = new ArrayList<>();
        result.forEach(arr -> {
            Image image = (Image)arr[1];
            image_list.add(image);
        });
        Double avg = (Double) result.get(0)[2];
        Long review_count = (Long) result.get(0)[3];
        return to_dto(ramyeon, image_list, avg, review_count);
    }
    
    @Override
    public void remove(Long mno) {
        ramyeon_repository.deleteById(mno);
    }

    @Transactional
    @Override
    public void modify(RamyeonDTO ramyeonDTO) {
        Optional<Ramyeon> result = ramyeon_repository.findById(ramyeonDTO.getMno());
        Map<String, Object> entity_map = to_entity(ramyeonDTO);
        List<Image> image_list = (List<Image>) entity_map.get("img_list");
        if(result.isPresent()){
            Ramyeon ramyeon = result.get();
            ramyeon.change_content(ramyeonDTO.getContent());
            ramyeon_repository.save(ramyeon);
            if(!image_list.isEmpty() && image_list.size() > 0){
                image_list.forEach(image ->{
                    image_repository.save(image);
                });
            }
        }
    }

    @Override
    public void remove_image(Long inum) {
        image_repository.deleteById(inum);
    }

    @Override
    public PageResultDTO<RamyeonDTO, Object[]> get_main_page(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.get_pageable();
        Page<Object[]> result = ramyeon_repository.get_main_page(pageable);
        Function<Object[], RamyeonDTO> fn = (arr -> to_dto(
            (Ramyeon)arr[0], 
            (List<Image>)(Arrays.asList((Image)arr[1])),
            (Double)arr[2],
            (Long)arr[3]
            ));
        return new PageResultDTO<>(result, fn);
    }

    @Override
    public PageResultDTO<RamyeonDTO, Object[]> get_main_page_best(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.get_pageable();
        Page<Object[]> result = ramyeon_repository.get_main_page_best(pageable);
        Function<Object[], RamyeonDTO> fn = (arr -> to_dto(
            (Ramyeon)arr[0], 
            (List<Image>)(Arrays.asList((Image)arr[1])),
            (Double)arr[2],
            (Long)arr[3]
            ));
        return new PageResultDTO<>(result, fn);
    }

    @Override
    public PageResultDTO<RamyeonDTO, Object[]> get_main_page_hot(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.get_pageable();
        Page<Object[]> result = ramyeon_repository.get_main_page_hot(pageable);
        Function<Object[], RamyeonDTO> fn = (arr -> to_dto(
            (Ramyeon)arr[0], 
            (List<Image>)(Arrays.asList((Image)arr[1])),
            (Double)arr[2],
            (Long)arr[3],
            (List<Review>)(Arrays.asList((Review)arr[4]))
            ));
        return new PageResultDTO<>(result, fn);
    }

    
}
