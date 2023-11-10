package com.myeon.suda.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.myeon.suda.entity.FreeBoard;

public interface FreeBoardRepository extends JpaRepository<FreeBoard, Long> {

    @Query("SELECT fb, w, COUNT(r) FROM FreeBoard fb " +
    " LEFT OUTER JOIN fb.writer w LEFT OUTER JOIN FreeBoardReply r " +
    " ON r.board = fb WHERE fb.boardCategory = 0 AND ( fb.freeBoardTitle LIKE CONCAT('%', :keyword,'%') " +
    " OR fb.freeBoardContent LIKE CONCAT('%', :keyword, '%') " +
    " OR w.nickname LIKE CONCAT('%', :keyword, '%') ) GROUP BY fb ")
    Page<Object[]> get_free_board_list(Pageable pageable, @Param("keyword") String keyword);

    @Query("SELECT fb, w, COUNT(r) FROM FreeBoard fb " +
    " LEFT OUTER JOIN fb.writer w LEFT OUTER JOIN FreeBoardReply r " +
    " ON r.board = fb WHERE fb.boardCategory = 1 AND ( fb.freeBoardTitle LIKE CONCAT('%', :keyword,'%') " +
    " OR fb.freeBoardContent LIKE CONCAT('%', :keyword, '%') " +
    " OR w.nickname LIKE CONCAT('%', :keyword, '%') ) GROUP BY fb ")
    Page<Object[]> get_recipe_board_list(Pageable pageable, @Param("keyword") String keyword);

    @Query("SELECT fb, w, COUNT(r) FROM FreeBoard fb " +
    " LEFT OUTER JOIN fb.writer w LEFT OUTER JOIN FreeBoardReply r " +
    " ON r.board = fb WHERE fb.freeBoardNo = :free_board_no GROUP BY fb")
    List<Object[]> get_board(@Param("free_board_no") Long free_board_no);

    @Query(value = "DELETE FROM free_board WHERE free_board.free_board_no = :free_board_no " +
            " AND free_board.board_category = :board_category", nativeQuery = true)
    void delete_board(Long free_board_no, Long board_category);

    @Query("SELECT fb, w, COUNT(r) FROM FreeBoard fb " +
    " LEFT OUTER JOIN fb.writer w  " +
    " LEFT OUTER JOIN FreeBoardReply r " +
    " ON r.board = fb GROUP BY fb ORDER BY fb.regDate DESC")
    Page<Object[]> get_list_community(Pageable pageable);
}
