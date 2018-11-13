package com.wellness.sseproject.web.controller.message;

import com.wellness.sseproject.web.controller.dto.LectureDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LectureResponseMessage extends ApiResponseMessage {

    private LectureDTO lecture;

    public LectureResponseMessage(){

    }
    public LectureResponseMessage(LectureDTO lectureDTO) {
        this.setHttpStatusCodeOK();
        this.lecture = lectureDTO;
    }

    public LectureResponseMessage(String errorMessage, String statusCode, LectureDTO lectureDTO) {
        super(errorMessage, statusCode);
        this.lecture = lectureDTO;
    }
}
