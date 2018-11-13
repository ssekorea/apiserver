package com.wellness.sseproject.web.controller.message;

import com.wellness.sseproject.domain.Course;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CourseResponseMessage extends ApiResponseMessage {

    Course course;

    public CourseResponseMessage(){

    }

    public CourseResponseMessage(Course course){
        this.course = course;
    }
}
