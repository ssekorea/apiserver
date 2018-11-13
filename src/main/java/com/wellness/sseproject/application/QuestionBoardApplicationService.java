package com.wellness.sseproject.application;

import com.wellness.sseproject.domain.QuestionBoard;
import com.wellness.sseproject.domain.repository.QuestionBoardRepository;
import com.wellness.sseproject.web.controller.dto.QuestionBoardModifyDTO;
import com.wellness.sseproject.web.controller.dto.QuestionBoardRegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class QuestionBoardApplicationService {

    @Autowired
    QuestionBoardRepository questionBoardRepository;

    public QuestionBoard registerQuestionBoard(QuestionBoardRegisterDTO questionBoardRegisterDTO){
        Date today = new Date();
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        questionBoardRegisterDTO.setRegisterDate(date.format(today.getTime()));

        return questionBoardRepository.saveAndFlush(new QuestionBoard(questionBoardRegisterDTO));
    }

    public QuestionBoard registerAnswerToQuestionBoard(QuestionBoardModifyDTO questionBoardModifyDTO) {

        QuestionBoard questionBoard = questionBoardRepository.findByBoardId(questionBoardModifyDTO.getBoardId());
        if (questionBoard == null){
            return null;
        }

        questionBoard.setAnswer(questionBoardModifyDTO.getAnswer());
        questionBoardRepository.saveAndFlush(questionBoard);

        return questionBoard;
    }

    public void deleteQuestionBoard(int boardId) {
        questionBoardRepository.deleteById(boardId);
    }
}
