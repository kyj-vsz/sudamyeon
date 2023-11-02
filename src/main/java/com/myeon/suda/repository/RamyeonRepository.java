package com.myeon.suda.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.myeon.suda.entity.Ramyeon;
import com.myeon.suda.repository.search.SearchRepository;

public interface RamyeonRepository extends JpaRepository<Ramyeon, Long>, SearchRepository{
    @Query("SELECT r, i, AVG(COALESCE(rv.grade,0)),COUNT(rv.rno) " +
            " FROM Ramyeon r LEFT OUTER JOIN Image i ON i.ramyeon = r " +
            " LEFT OUTER JOIN Review rv ON rv.ramyeon = r " +
            " WHERE r.mno = :mno GROUP BY i ")        
    List<Object[]> get_ramyeon_with_all(@Param("mno") Long mno);


    @Query("SELECT r, i, AVG(COALESCE(rv.grade,0)), COUNT(rv.rno) "+
    " FROM Ramyeon r LEFT OUTER JOIN Image i ON i.ramyeon = r " +
    " LEFT OUTER JOIN Review rv ON rv.ramyeon = r WhERE r.gname LIKE CONCAT('%', :keyword, '%') OR " +
    " r.brand LIKE CONCAT('%', :keyword, '%') OR r.category LIKE CONCAT('%', :keyword, '%') " +
    " GROUP BY r ")
    Page<Object[]> get_list(Pageable pageable, @Param("keyword") String keyword);

    @Query("SELECT r,  i, AVG(COALESCE(rv.grade,0)), COUNT(rv.rno)"+
    " FROM Ramyeon r LEFT OUTER JOIN Image i ON i.ramyeon = r " +
    " LEFT OUTER JOIN Review rv ON rv.ramyeon = r GROUP BY r " +
    " ORDER BY r.regDate DESC")
    Page<Object[]> get_main_page(Pageable pageable);

    @Query("SELECT r, i, AVG(COALESCE(rv.grade,0)), COUNT(rv.rno) " +
    " FROM Ramyeon r LEFT OUTER JOIN Image i ON i.ramyeon = r " +
    " LEFT OUTER JOIN Review rv ON rv.ramyeon = r " + 
    " GROUP BY r order by (AVG(COALESCE(rv.grade,0))) desc")
    Page<Object[]> get_main_page_best(Pageable pageable);

    @Query("SELECT r, i, AVG(COALESCE(rv.grade,0)), COUNT(rv.rno), rv" +
    " FROM Ramyeon r LEFT OUTER JOIN Image i ON i.ramyeon = r " +
    " LEFT OUTER JOIN Review rv ON rv.ramyeon = r " +
    " GROUP BY r order by COUNT(rv.rno) desc")
    Page<Object[]> get_main_page_hot(Pageable pageable);

    @Query("SELECT r, i, AVG(COALESCE(rv.grade,0)), COUNT(rv.rno) "+
    " FROM Ramyeon r LEFT OUTER JOIN Image i ON i.ramyeon = r " +
    " LEFT OUTER JOIN Review rv ON rv.ramyeon = r WHERE r.brand " +
    " IN (:brand) GROUP BY r ")
    Page<Object[]> get_list_check_page(Pageable pageable, @Param("brand") List<String> brand);

    @Query("SELECT r, i, AVG(COALESCE(rv.grade,0)), COUNT(rv.rno), rv" +
    " FROM Ramyeon r LEFT OUTER JOIN Image i ON i.ramyeon = r " +
    " LEFT OUTER JOIN Review rv ON rv.ramyeon = r " +
    " GROUP BY rv order by rv.modDate desc")
    Page<Object[]> get_main_page_new_review(Pageable pageable);


}
