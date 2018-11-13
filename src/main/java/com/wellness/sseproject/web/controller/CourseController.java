package com.wellness.sseproject.web.controller;

import com.wellness.sseproject.application.CourseApplicationService;
import com.wellness.sseproject.application.CourseQueryService;
import com.wellness.sseproject.domain.Course;
import com.wellness.sseproject.web.controller.dto.CourseLectureDTO;
import com.wellness.sseproject.web.controller.dto.CourseModifyDTO;
import com.wellness.sseproject.web.controller.message.ApiResponseMessage;
import com.wellness.sseproject.web.controller.message.CourseLectureResponseMessage;
import com.wellness.sseproject.web.controller.message.CourseResponseMessage;
import com.wellness.sseproject.web.controller.message.ErrorResponseMessageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/courses")
public class CourseController {

    @Autowired
    CourseApplicationService courseApplicationService;

    @Autowired
    CourseQueryService courseQueryService;

    @RequestMapping(value = "/{courseId}", method = RequestMethod.GET)
    public ResponseEntity getCourseByCourseId(@PathVariable int courseId) {
        CourseLectureDTO courseLectureDTO = courseQueryService.getCourseLectureByCourseId(courseId);

        if (courseLectureDTO == null){
            return new ResponseEntity<>(ErrorResponseMessageFactory.createErrorResponseMessageFactory("Invalid courseId", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
        ApiResponseMessage apiResponseMessage = new CourseLectureResponseMessage(courseLectureDTO);
        return new ResponseEntity<>(apiResponseMessage, HttpStatus.OK);
    }


    @RequestMapping(value = "/{courseId}", method = RequestMethod.DELETE)
    public ResponseEntity<ApiResponseMessage> deleteCourse(@PathVariable int courseId) {

        if (!courseApplicationService.deleteCourseByCourseId(courseId)) {
            return new ResponseEntity<>(ErrorResponseMessageFactory.createErrorResponseMessageFactory("Invalid courseId", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
        ApiResponseMessage apiResponseMessage = new ApiResponseMessage();
        apiResponseMessage.setHttpStatusCodeOK();
        return new ResponseEntity<>(apiResponseMessage, HttpStatus.OK);
    }

    @RequestMapping(value = "/{courseId}", method = RequestMethod.PUT)
    public ResponseEntity modifyCourse(@PathVariable int courseId, @RequestBody CourseModifyDTO courseModifyDTO) {
        courseModifyDTO.setCourseId(courseId);
        Course course = courseApplicationService.modifyCourse(courseId, courseModifyDTO);

        if (course == null){
            return new ResponseEntity<>(ErrorResponseMessageFactory.createErrorResponseMessageFactory("Invalid courseId", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
        ApiResponseMessage apiResponseMessage = new CourseResponseMessage(course);
        apiResponseMessage.setHttpStatusCodeOK();
        return new ResponseEntity<>(apiResponseMessage, HttpStatus.OK);
    }
}
