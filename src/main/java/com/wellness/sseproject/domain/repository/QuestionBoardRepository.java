package com.wellness.sseproject.domain.repository;

import com.wellness.sseproject.domain.QuestionBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionBoardRepository extends JpaRepository<QuestionBoard, Integer> {

    QuestionBoard findByBoardId(int boardId);

    @Query(value = "SELECT * FROM `QUESTION_BOARD_TB` ORDER BY answer LIMIT :startIndex, :pageCount", nativeQuery = true)
    List<QuestionBoard> getQuestionBoardListByPage(@Param("startIndex")int startIndex, @Param("pageCount")int pageCount);

    @Query(value = "SELECT * FROM `QUESTION_BOARD_TB` WHERE user_id = :userId", nativeQuery = true)
    List<QuestionBoard> getQuestionBoardsByUserId(@Param("userId")String userId);
}
