package com.wellness.sseproject.web.controller.dto;

import com.wellness.sseproject.domain.Course;
import com.wellness.sseproject.domain.Lecture;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class CourseLectureDTO {

    Course course;

    LectureDTO lecture;

    public CourseLectureDTO(Course course){
        this.course = course;
    }

    public CourseLectureDTO(Course course, Lecture lecture) {
        this.course = course;
        this.lecture = new LectureDTO(lecture);
    }

    public CourseLectureDTO(Course course, LectureDTO lectureDTO){
        this.course = course;
        this.lecture = lectureDTO;
    }
}
