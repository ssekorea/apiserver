package com.wellness.sseproject.web.controller.message;

import com.wellness.sseproject.web.controller.dto.CourseLectureDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CourseLectureResponseMessage extends ApiResponseMessage {

    CourseLectureDTO courseLecture;

    public CourseLectureResponseMessage(CourseLectureDTO courseLecture){
        this.courseLecture = courseLecture;
        this.setHttpStatusCodeOK();
    }
}
