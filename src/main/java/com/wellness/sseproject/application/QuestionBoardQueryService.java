package com.wellness.sseproject.application;

import com.wellness.sseproject.domain.QuestionBoard;
import com.wellness.sseproject.domain.repository.QuestionBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionBoardQueryService {

    @Autowired
    QuestionBoardRepository questionBoardRepository;


    public List<QuestionBoard> getQuestionBoardListByPage(int startIndex, int count) {

        List<QuestionBoard> questionBoardList = questionBoardRepository.getQuestionBoardListByPage(startIndex, count);

        if (questionBoardList == null){
            return null;
        }
        return questionBoardList;
    }

    public QuestionBoard getQuestionBoard(int boardId) {
        return questionBoardRepository.findByBoardId(boardId);
    }

    public List<QuestionBoard> getQuestionBoardsByUserId(String userId) {
        List<QuestionBoard> questionBoardList = questionBoardRepository.getQuestionBoardsByUserId(userId);

        if (questionBoardList.size() == 0){
            return null;
        }
        return questionBoardRepository.getQuestionBoardsByUserId(userId);
    }
}
