package com.wellness.sseproject.web.controller.message;

import com.wellness.sseproject.domain.Course;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Service
public class UserCoursesResponseMessage extends ApiResponseMessage {

    private List<Course> courses;

}
