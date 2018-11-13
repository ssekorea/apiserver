package com.wellness.sseproject.web.controller.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class QuestionBoardModifyDTO {

    int boardId;
    String answer;
}
