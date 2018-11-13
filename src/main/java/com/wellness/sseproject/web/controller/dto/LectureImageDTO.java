package com.wellness.sseproject.web.controller.dto;


import com.wellness.sseproject.domain.LectureImage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LectureImageDTO {

    private int id;
    private int lectureId;
    private String imageUrl;

    public LectureImageDTO(int id, int lectureId, String imageUrl) {
        this.id = id;
        this.lectureId = lectureId;
        this.imageUrl = imageUrl;
    }

    public LectureImageDTO(LectureImage lectureImage){
        this.id = lectureImage.getId();
        this.lectureId = lectureImage.getLecture().getLectureId();
        this.imageUrl = lectureImage.getImageUrl();
    }
}
