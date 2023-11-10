package com.myeon.suda.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.myeon.suda.entity.FreeBoard;
import com.myeon.suda.entity.FreeBoardReply;

public interface FreeBoardReplyRepository extends JpaRepository<FreeBoardReply, Long>{

    @EntityGraph(attributePaths = {"member"}, type = EntityGraph.EntityGraphType.FETCH)
    List<FreeBoardReply> findByBoard(FreeBoard board, Pageable pageable);

    @Query(countQuery = "SELECT COUNT(r.board) FROM FreeBoardReply r WHERE r.board = :board")
    int countByBoard(FreeBoard board);
}
