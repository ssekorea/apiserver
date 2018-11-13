package com.wellness.sseproject.domain;

import com.wellness.sseproject.web.controller.dto.QuestionBoardRegisterDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Data
@Table(name = "QUESTION_BOARD_TB")
public class QuestionBoard {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int boardId;

    @Column(name = "user_id")
    String userId;

    @Column(name = "register_date")
    String registerDate;

    @Column(name = "title")
    String title;

    @Column(name = "contents")
    String contents;

    @Column(name = "answer")
    String answer;

    public QuestionBoard(QuestionBoardRegisterDTO questionBoardRegisterDTO){
        this.userId = questionBoardRegisterDTO.getUserId();
        this.registerDate = questionBoardRegisterDTO.getRegisterDate();
        this.title = questionBoardRegisterDTO.getTitle();
        this.contents = questionBoardRegisterDTO.getContents();
    }

    public QuestionBoard(String userId, String registerDate, String title, String contents, String answer) {
        this.userId = userId;
        this.registerDate = registerDate;
        this.title = title;
        this.contents = contents;
        this.answer = answer;
    }

    public QuestionBoard(String userId, String registerDate, String title, String contents) {
        this.userId = userId;
        this.registerDate = registerDate;
        this.title = title;
        this.contents = contents;
    }

}
