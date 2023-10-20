package com.myeon.suda.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.myeon.suda.dto.RamyeonDTO;
import com.myeon.suda.entity.Image;
import com.myeon.suda.entity.Ramyeon;
import com.myeon.suda.repository.RamyeonRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RamyeonServiceImpl implements RamyeonService {
    private final RamyeonRepository ramyeon_repository;

    @Override
    public RamyeonDTO get_ramyeon(Long mno) {
      List<Object[]> result = ramyeon_repository.get_ramyeon_with_all(mno);
        Ramyeon ramyeon =(Ramyeon) result.get(0)[0];
        List<Image> image_list = new ArrayList<>();
        result.forEach(arr -> {
            Image image = (Image)arr[1];
            image_list.add(image);
        });
        Double avg = (Double) result.get(0)[2];
        Long review_count = (Long) result.get(0)[3];
        return to_dto(ramyeon, image_list, avg, review_count);
    }
    
}
