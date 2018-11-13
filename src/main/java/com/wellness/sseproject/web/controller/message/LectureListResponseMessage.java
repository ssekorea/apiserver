package com.wellness.sseproject.web.controller.message;

import com.wellness.sseproject.web.controller.dto.LectureDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LectureListResponseMessage extends ApiResponseMessage {

    private List<LectureDTO> lectures;

    public LectureListResponseMessage(List<LectureDTO> lectureDTO) {
        this.lectures = lectureDTO;
        this.setHttpStatusCodeOK();
    }

}
