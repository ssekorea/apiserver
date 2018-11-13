package com.wellness.sseproject.application;

import com.wellness.sseproject.domain.Course;
import com.wellness.sseproject.domain.repository.CourseRepository;
import com.wellness.sseproject.domain.repository.LectureRepository;
import com.wellness.sseproject.domain.repository.UserRepository;
import com.wellness.sseproject.web.controller.dto.CourseModifyDTO;
import com.wellness.sseproject.web.controller.dto.CourseRegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class CourseApplicationService{


    @Autowired
    UserRepository userRepository;

    @Autowired
    LectureRepository lectureRepository;

    @Autowired
    CourseRepository courseRepository;

    public boolean registerCourse(String userId, CourseRegisterDTO courseRegisterDTO) {

        if (userRepository.countByUserId(userId) == 0 && lectureRepository.countByLectureId(courseRegisterDTO.getLectureId()) == 0){
            return false;
        }
        // 선생, 학생 구별 로직 들어가야함
        Course course = new Course(courseRegisterDTO.getLectureId(), userId,courseRegisterDTO.getAttendType());
        Date today = new Date();
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        course.setRegisterDate(date.format(today.getTime()));
        courseRepository.save(course);
        return true;

    }

    public boolean deleteCourseByCourseId(int courseId) {
        if (courseRepository.countCourseByCourseId(courseId) == 0){
            return false;
        }
        courseRepository.deleteById(courseId);
        return true;
    }

    public Course modifyCourse(int courseId, CourseModifyDTO courseModifyDTO){
        Course course = courseRepository.getCourseByCourseId(courseId);
        if (course == null){
            return null;
        }
        course.setAttendFlag(courseModifyDTO.getAttendFlag());
        course.setAttendType(courseModifyDTO.getAttendType());
        course.setCompletionFlag(courseModifyDTO.getCompletionFlag());
        course.setPaymentInfo(courseModifyDTO.getPaymentInfo());
        course.setPaymentConfirm(courseModifyDTO.isPaymentConfirm());
        courseRepository.save(course);
        return course;
    }
}
