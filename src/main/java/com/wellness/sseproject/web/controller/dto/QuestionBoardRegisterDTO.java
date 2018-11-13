package com.wellness.sseproject.web.controller.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Data
@Setter
public class QuestionBoardRegisterDTO {

    private String userId;

    private String registerDate;

    private String title;

    private String contents;

}
