package com.wellness.sseproject.domain;

import com.wellness.sseproject.web.controller.dto.LectureDTO;
import com.wellness.sseproject.web.controller.dto.LectureModifyDTO;
import com.wellness.sseproject.web.controller.dto.LectureRegisterDTO;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;


@Entity
@Data
@ToString
@Table(name = "LECTURE_TB")
public class Lecture {

    @Id
    @Column(name = "lecture_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int lectureId;

    @Column(name = "title")
    private String title;
    @Column(name = "start_time")
    private String startTime;
    @Column(name = "end_time")
    private String endTime;
    @Column(name = "place_address1")
    private String placeAddress1;
    @Column(name = "place_address2")
    private String placeAddress2;
    @Column(name = "place_address3")
    private String placeAddress3;
    @Column(name = "explanation")
    private String explanation;
    @Column(name = "lecture_type")
    private String lectureType;
    @Column(name = "max_teacher_num")
    private int maxTeacherNum;
    @Column(name = "max_student_num")
    private int maxStudentNum;

    @Column(name = "current_student_num")
    private int currentStudentNum;
    @Column(name = "current_teacher_num")
    private int currentTeacherNum;

    @Column(name = "masking")
    private boolean masking;

//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name="lecture_id", referencedColumnName = "lecture_id")
//    private List<LectureImage> lectureImages;
    public Lecture(){

    }

    public Lecture(LectureDTO lectureDTO){
        this.title = lectureDTO.getTitle();
        this.startTime = lectureDTO.getStartTime();
        this.endTime = lectureDTO.getEndTime();
        this.placeAddress1 = lectureDTO.getPlaceAddress1();
        this.placeAddress2 = lectureDTO.getPlaceAddress2();
        this.placeAddress3 = lectureDTO.getPlaceAddress3();
        this.maxStudentNum = lectureDTO.getMaxStudentNum();
        this.explanation = lectureDTO.getExplanation();
        this.lectureType = lectureDTO.getLectureType();
        this.maxTeacherNum = lectureDTO.getMaxTeacherNum();
//        lectureImages = new ArrayList<>();
//        for (String lectureImageUrl : lecture.getLectureImageUrls()){
//            LectureImage tempLectureImage = new LectureImage(lectureImageUrl);
//            tempLectureImage.setLecture(this);
//            lectureImages.add(tempLectureImage);
//        }
    }

    public Lecture(LectureModifyDTO lectureModifyDTO){
        this.lectureId = lectureModifyDTO.getLectureId();
        this.title = lectureModifyDTO.getTitle();
        this.startTime = lectureModifyDTO.getStartTime();
        this.endTime = lectureModifyDTO.getEndTime();
        this.placeAddress1 = lectureModifyDTO.getPlaceAddress1();
        this.placeAddress2 = lectureModifyDTO.getPlaceAddress2();
        this.placeAddress3 = lectureModifyDTO.getPlaceAddress3();
        this.maxStudentNum = lectureModifyDTO.getMaxStudentNum();
        this.maxTeacherNum = lectureModifyDTO.getMaxTeacherNum();
        this.lectureType = lectureModifyDTO.getLectureType();
        this.explanation = lectureModifyDTO.getExplanation();
        this.masking = true;

    }

    public Lecture(LectureRegisterDTO lectureRegisterDTO){
        this.title = lectureRegisterDTO.getTitle();
        this.startTime = lectureRegisterDTO.getStartTime();
        this.endTime = lectureRegisterDTO.getEndTime();
        this.placeAddress1 = lectureRegisterDTO.getPlaceAddress1();
        this.placeAddress2 = lectureRegisterDTO.getPlaceAddress2();
        this.placeAddress3 = lectureRegisterDTO.getPlaceAddress3();
        this.maxStudentNum = lectureRegisterDTO.getMaxStudentNum();
        this.maxTeacherNum = lectureRegisterDTO.getMaxTeacherNum();
        this.lectureType = lectureRegisterDTO.getLectureType();
        this.explanation = lectureRegisterDTO.getExplanation();
        this.masking = true;
    }
}
