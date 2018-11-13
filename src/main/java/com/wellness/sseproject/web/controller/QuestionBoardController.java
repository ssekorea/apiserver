package com.wellness.sseproject.web.controller;

import com.wellness.sseproject.application.QuestionBoardApplicationService;
import com.wellness.sseproject.application.QuestionBoardQueryService;
import com.wellness.sseproject.domain.QuestionBoard;
import com.wellness.sseproject.web.controller.dto.QuestionBoardModifyDTO;
import com.wellness.sseproject.web.controller.dto.QuestionBoardRegisterDTO;
import com.wellness.sseproject.web.controller.message.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/boards")
public class QuestionBoardController {

    @Autowired
    private QuestionBoardApplicationService questionBoardApplicationService;

    @Autowired
    private QuestionBoardQueryService questionBoardQueryService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ApiResponseMessage> getBoardList(@RequestParam(defaultValue = "0")int startIndex, @RequestParam(defaultValue = "10")int count){

        List<QuestionBoard> questionBoardList = questionBoardQueryService.getQuestionBoardListByPage(startIndex, count);
        if (questionBoardList == null){
            new ResponseEntity<>(ErrorResponseMessageFactory.createErrorResponseMessageFactory("There is no page", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
        ApiResponseMessage apiResponseMessage = new QuestionBoardListResponseMessage(questionBoardList);
        return new ResponseEntity<>(apiResponseMessage, HttpStatus.OK);
    }

    @RequestMapping(value = "/{boardId}", method = RequestMethod.GET)
    public ResponseEntity<ApiResponseMessage> getQuestionBoard(@PathVariable int boardId){

        QuestionBoard questionBoard = questionBoardQueryService.getQuestionBoard(boardId);
        if (questionBoard == null){
            new ResponseEntity<>(ErrorResponseMessageFactory.createErrorResponseMessageFactory("Invalid board Id", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }

        ApiResponseMessage apiResponseMessage = new QuestionBoardResponseMessage(questionBoard);
        return new ResponseEntity<>(apiResponseMessage, HttpStatus.OK);
    }

    @RequestMapping(value = "/{boardId}", method = RequestMethod.DELETE)
    public ResponseEntity<ApiResponseMessage> deleteQuestionBoard(@PathVariable int boardId){

        if (questionBoardQueryService.getQuestionBoard(boardId) == null){
            new ResponseEntity<>(ErrorResponseMessageFactory.createErrorResponseMessageFactory("Invalid board Id", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
        questionBoardApplicationService.deleteQuestionBoard(boardId);

        ApiResponseMessage apiResponseMessage = new ApiResponseMessage();
        apiResponseMessage.setHttpStatusCodeOK();

        return new ResponseEntity<>(apiResponseMessage, HttpStatus.OK);
    }

    @RequestMapping(value = "/{boardId}", method = RequestMethod.PUT)
    public ResponseEntity<ApiResponseMessage> registerAnswer(@PathVariable int boardId, @RequestBody QuestionBoardModifyDTO questionBoardModifyDTO){
        questionBoardModifyDTO .setBoardId(boardId);
        QuestionBoard questionBoard = questionBoardApplicationService.registerAnswerToQuestionBoard(questionBoardModifyDTO);
        if (questionBoard == null){
            new ResponseEntity<>(ErrorResponseMessageFactory.createErrorResponseMessageFactory("Invalid board Id", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
        ApiResponseMessage apiResponseMessage = new QuestionBoardResponseMessage(questionBoard);
        return new ResponseEntity<>(apiResponseMessage, HttpStatus.OK);
    }


}
