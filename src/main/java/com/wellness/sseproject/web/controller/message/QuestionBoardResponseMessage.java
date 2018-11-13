package com.wellness.sseproject.web.controller.message;

import com.wellness.sseproject.domain.QuestionBoard;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionBoardResponseMessage extends ApiResponseMessage {

    private QuestionBoard questionBoard;

    public QuestionBoardResponseMessage(QuestionBoard questionBoard) {
        this.questionBoard = questionBoard;
        this.setHttpStatusCodeOK();
    }
}
