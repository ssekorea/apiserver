package com.wellness.sseproject.web.controller.message;

import com.wellness.sseproject.domain.Course;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data
public class CourseListResponseMessage extends ApiResponseMessage {

    private List<Course> courseList;

    public CourseListResponseMessage(List<Course> courseList) {
        this.courseList = courseList;
        this.setHttpStatusCodeOK();
    }
}
