package com.wellness.sseproject.web.controller.message;

import com.wellness.sseproject.domain.QuestionBoard;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuestionBoardListResponseMessage extends ApiResponseMessage {

    private List<QuestionBoard> questionBoardList;

    public QuestionBoardListResponseMessage(List<QuestionBoard> questionBoardList){
        this.questionBoardList = questionBoardList;
        this.setHttpStatusCodeOK();
    }
}
