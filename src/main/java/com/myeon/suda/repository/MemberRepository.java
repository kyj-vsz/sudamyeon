package com.myeon.suda.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.myeon.suda.entity.Member;




public interface MemberRepository extends JpaRepository<Member, String> {

    
    @EntityGraph(attributePaths = {"roleSet"},type = EntityGraph.EntityGraphType.LOAD)
    @Query("select m from Member m where m.emailId = :emailId")
    Optional<Member> findByEmailId(@Param("emailId") String emailId);
}
