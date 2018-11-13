package com.wellness.sseproject.web.controller.message;

import com.wellness.sseproject.web.controller.dto.CourseLectureDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CourseLectureListResponseMessage extends ApiResponseMessage {

    List<CourseLectureDTO> courseLectureList;

    public CourseLectureListResponseMessage(List<CourseLectureDTO> courseLecture){
        this.courseLectureList = courseLecture;
        this.setHttpStatusCodeOK();
    }
}