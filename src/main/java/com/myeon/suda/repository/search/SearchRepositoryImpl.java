package com.myeon.suda.repository.search;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.myeon.suda.dto.PageRequestDTO;
import com.myeon.suda.entity.QImage;
import com.myeon.suda.entity.QRamyeon;
import com.myeon.suda.entity.QReview;
import com.myeon.suda.entity.Ramyeon;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;

public class SearchRepositoryImpl extends QuerydslRepositorySupport implements SearchRepository {  

    public SearchRepositoryImpl() {
        super(Ramyeon.class);
    }

    @Override
    public Page<Object[]> search_page(PageRequestDTO pageRequestDTO, Pageable pageable) {
        System.out.println("--------서치페이지");
        QRamyeon ramyeon = QRamyeon.ramyeon;
        QReview review = QReview.review;
        QImage image = QImage.image;
        
        JPQLQuery<Ramyeon> jpql_query = from(ramyeon);
        jpql_query.leftJoin(image).on(image.ramyeon.eq(ramyeon));
        jpql_query.leftJoin(review).on(review.ramyeon.eq(ramyeon));
        JPQLQuery<Tuple> tuple = jpql_query.select(ramyeon,image,review.grade.avg().coalesce((double) 0),review.count());

        BooleanBuilder boolean_builder = new BooleanBuilder();
        BooleanExpression expression = ramyeon.mno.gt(0L);

        boolean_builder.and(expression);
        
        BooleanBuilder condition_builder = new BooleanBuilder();
        if(pageRequestDTO.getBrand() != null && pageRequestDTO.getBrand().size() != 0){
            condition_builder.and(ramyeon.brand.in(pageRequestDTO.getBrand()));
        }
        if(pageRequestDTO.getCooking() != null && pageRequestDTO.getCooking().size() != 0){
            condition_builder.and(ramyeon.cooking.in(pageRequestDTO.getCooking()));
        }
        if(pageRequestDTO.getCategory() != null && pageRequestDTO.getCategory().size() != 0){
            condition_builder.and(ramyeon.category.in(pageRequestDTO.getCategory()));
        }
        
        boolean_builder.and(condition_builder);        
        
        tuple.where(boolean_builder);

        tuple.groupBy(ramyeon);

        tuple.orderBy(ramyeon.mno.desc());

        tuple.offset(pageable.getOffset());
        tuple.limit(pageable.getPageSize());

        List<Tuple> result = tuple.fetch();

        long count = tuple.fetchCount();

        return new PageImpl<Object[]>(result.stream().map(t -> t.toArray()).collect(Collectors.toList()), pageable, count);
    }
    
}
