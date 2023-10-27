package com.myeon.suda.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.myeon.suda.dto.ImageDTO;
import com.myeon.suda.dto.PageRequestDTO;
import com.myeon.suda.dto.PageResultDTO;
import com.myeon.suda.dto.RamyeonDTO;
import com.myeon.suda.entity.Image;
import com.myeon.suda.entity.Ramyeon;

public interface RamyeonService {
    Long register(RamyeonDTO ramyeonDTO);

    PageResultDTO<RamyeonDTO, Object[]> get_list_page(PageRequestDTO requestDTO);

    RamyeonDTO get_ramyeon(Long mno);

    void remove(Long mno);

    void modify(RamyeonDTO ramyeonDTO);
    
    void remove_image(Long inum);
    
    RamyeonDTO get_img();

    RamyeonDTO get_img2();

    RamyeonDTO get_img3();
    
    default RamyeonDTO to_dto(Ramyeon ramyeon, List<Image> images, Double avg, Long review_count){
        RamyeonDTO ramyeonDTO = RamyeonDTO.builder()
                    .mno(ramyeon.getMno())
                    .gname(ramyeon.getGname())
                    .content(ramyeon.getContent())
                    .brand(ramyeon.getBrand())
                    .price(ramyeon.getPrice())
                    .weight(ramyeon.getWeight())
                    .calorie(ramyeon.getCalorie())
                    .category(ramyeon.getCategory())
                    .cooking(ramyeon.getCooking())
                    .expiry(ramyeon.getExpiry())
                    .birth(ramyeon.getBirth())
                    .spicy(ramyeon.getSpicy())
                    .cooktime(ramyeon.getCooktime())
                    .soupnum(ramyeon.getSoupnum())
                    .noodle_size(ramyeon.getNoodleSize())
                    .sodum(ramyeon.getSodum())
                    .carbohydrate(ramyeon.getCarbohydrate())
                    .protein(ramyeon.getProtein())
                    .sugar(ramyeon.getSugar())
                    .fat(ramyeon.getFat())
                    .saturated_fat(ramyeon.getSaturatedFat())
                    .trans_fat(ramyeon.getTransFat())
                    .cholesterol(ramyeon.getCholesterol())
                    .etc(ramyeon.getEtc())
                    .regdate(ramyeon.getRegDate())
                    .moddate(ramyeon.getModDate())
                    .build();
        
        List<ImageDTO> imageDTO_list = images.stream().map(image -> {
            return ImageDTO.builder()
                        .inum(image.getInum())
                        .img_name(image.getImgName())
                        .path(image.getPath())
                        .uuid(image.getUuid())
                        .build();
        }).collect(Collectors.toList());

        ramyeonDTO.setImageDTO_list(imageDTO_list);
        ramyeonDTO.setAvg(avg);
        ramyeonDTO.setReview_count(review_count.intValue());

        return ramyeonDTO;
    }

    default Map<String, Object> to_entity(RamyeonDTO ramyeonDTO){
        Map<String, Object> entity_map = new HashMap<>();

        Ramyeon ramyeon = Ramyeon.builder()
                    .mno(ramyeonDTO.getMno())
                    .gname(ramyeonDTO.getGname())
                    .content(ramyeonDTO.getContent())
                    .brand(ramyeonDTO.getBrand())
                    .price(ramyeonDTO.getPrice())
                    .weight(ramyeonDTO.getWeight())
                    .calorie(ramyeonDTO.getCalorie())
                    .category(ramyeonDTO.getCategory())
                    .cooking(ramyeonDTO.getCooking())
                    .expiry(ramyeonDTO.getExpiry())
                    .birth(ramyeonDTO.getBirth())
                    .spicy(ramyeonDTO.getSpicy())
                    .cooktime(ramyeonDTO.getCooktime())
                    .soupnum(ramyeonDTO.getSoupnum())
                    .noodleSize(ramyeonDTO.getNoodle_size())
                    .sodum(ramyeonDTO.getSodum())
                    .carbohydrate(ramyeonDTO.getCarbohydrate())
                    .protein(ramyeonDTO.getProtein())
                    .sugar(ramyeonDTO.getSugar())
                    .fat(ramyeonDTO.getFat())
                    .saturatedFat(ramyeonDTO.getSaturated_fat())
                    .transFat(ramyeonDTO.getTrans_fat())
                    .cholesterol(ramyeonDTO.getCholesterol())
                    .etc(ramyeonDTO.getEtc())
                    .build();

        entity_map.put("ramyeon", ramyeon);

        List<ImageDTO> imageDTO_list = ramyeonDTO.getImageDTO_list();
        
        List<Image> image_list = imageDTO_list.stream().map(imageDTO -> {
            Image image = Image.builder()
                    .inum(imageDTO.getInum())
                    .imgName(imageDTO.getImg_name())
                    .path(imageDTO.getPath())
                    .uuid(imageDTO.getUuid())
                    .ramyeon(ramyeon)
                    .build();
            return image;
        }).collect(Collectors.toList());

        entity_map.put("img_list", image_list);

        return entity_map;
    }
}
