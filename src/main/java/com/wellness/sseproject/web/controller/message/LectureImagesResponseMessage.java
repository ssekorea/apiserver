package com.wellness.sseproject.web.controller.message;

import com.wellness.sseproject.domain.LectureImage;
import com.wellness.sseproject.web.controller.dto.LectureImageDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LectureImagesResponseMessage extends ApiResponseMessage {

    List<LectureImageDTO> lectureImages;

    public LectureImagesResponseMessage(){

    }
    public LectureImagesResponseMessage(List<LectureImageDTO> lectureImages) {
        this.lectureImages = lectureImages;
    }

    public LectureImagesResponseMessage(String errorMessage, String statusCode, List<LectureImageDTO> lectureImages) {
        super(errorMessage, statusCode);
        this.lectureImages = lectureImages;
    }
}
