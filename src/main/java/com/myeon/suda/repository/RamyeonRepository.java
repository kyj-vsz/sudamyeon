package com.myeon.suda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.myeon.suda.entity.Ramyeon;

public interface RamyeonRepository extends JpaRepository<Ramyeon, Long>{
    @Query("SELECT r, i, AVG(COALESCE(rv.grade,0)),COUNT(rv.rno) " +
    " FROM Ramyeon r LEFT OUTER JOIN Image i ON i.ramyeon = r " +
    " LEFT OUTER JOIN Review rv ON rv.ramyeon = r " +
    " WHERE r.mno = :mno GROUP BY i ")        
    List<Object[]> get_ramyeon_with_all(@Param("mno") Long mno);
    
    @Query("SELECT r, i, AVG(COALESCE(rv.grade,0)), COUNT(rv.rno) " +
    " FROM Ramyeon r LEFT OUTER JOIN Image i ON i.ramyeon = r " +
    " LEFT OUTER JOIN Review rv ON rv.ramyeon = r " +
    " GROUP BY i order by AVG(COALESCE(rv.grade,0)) desc")
    List<Object[]> get_ramyeon_img();
}
