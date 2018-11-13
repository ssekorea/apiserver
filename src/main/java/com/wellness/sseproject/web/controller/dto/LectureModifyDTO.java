package com.wellness.sseproject.web.controller.dto;


import com.wellness.sseproject.domain.Lecture;
import com.wellness.sseproject.domain.LectureImage;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class LectureModifyDTO{

    private int lectureId;
    private String title;
    private String startTime;
    private String endTime;
    private String placeAddress1;
    private String placeAddress2;
    private String placeAddress3;
    private String explanation;
    private String lectureType;
    private int maxTeacherNum;
    private int maxStudentNum;
    private List<String> lectureImageUrls = new ArrayList<>();

    public LectureModifyDTO(int lectureId, String title, String startTime, String endTime, String placeAddress1, String placeAddress2, String placeAddress3, String explanation, String lectureType, int maxTeacherNum, int maxStudentNum, List<String> lectureImageUrls) {
        this.lectureId = lectureId;
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.placeAddress1 = placeAddress1;
        this.placeAddress2 = placeAddress2;
        this.placeAddress3 = placeAddress3;
        this.explanation = explanation;
        this.lectureType = lectureType;
        this.maxTeacherNum = maxTeacherNum;
        this.maxStudentNum = maxStudentNum;
        this.lectureImageUrls = lectureImageUrls;
    }

    public LectureModifyDTO(Lecture lecture){
        this.lectureId = lecture.getLectureId();
        this.title = lecture.getTitle();
        this.startTime = lecture.getStartTime();
        this.endTime = lecture.getEndTime();
        this.placeAddress1 = lecture.getPlaceAddress1();
        this.placeAddress2 = lecture.getPlaceAddress2();
        this.placeAddress3 = lecture.getPlaceAddress3();
        this.explanation = lecture.getExplanation();
        this.lectureType = lecture.getLectureType();
        this.maxTeacherNum = lecture.getMaxTeacherNum();
        this.maxStudentNum = lecture.getMaxStudentNum();
//        this.lectureImageUrls = new ArrayList<>();
//        for (LectureImage lectureImage : lecture.getLectureImages()){
//            lectureImageUrls.add(lectureImage.getImageUrl());
//        }
    }
}
