package com.myeon.suda.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myeon.suda.dto.RamyeonDTO;
import com.myeon.suda.entity.Image;
import com.myeon.suda.entity.Ramyeon;
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
    public RamyeonDTO get_img() {
      List<Object[]> result = ramyeon_repository.get_ramyeon_img();
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

    
}
