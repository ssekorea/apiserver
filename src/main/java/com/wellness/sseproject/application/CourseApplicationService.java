package com.wellness.sseproject.application;

import com.wellness.sseproject.domain.Course;
import com.wellness.sseproject.domain.Lecture;
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
        Lecture lecture = lectureRepository.findLectureByLectureId(courseRegisterDTO.getLectureId());
        if (courseRepository.getCourseByUserIdAndCourseId(userId, courseRegisterDTO.getLectureId()) != null){
            return false;
        }

        if (courseRegisterDTO.getAttendType() == 0){
            if (lecture.getMaxStudentNum() - lecture.getCurrentStudentNum() > 0){
                lecture.setCurrentStudentNum(lecture.getCurrentStudentNum() + 1);
                lectureRepository.saveAndFlush(lecture);

                Course course = new Course(courseRegisterDTO.getLectureId(), userId,courseRegisterDTO.getAttendType());
                Date today = new Date();
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
                course.setRegisterDate(date.format(today.getTime()));
                courseRepository.save(course);
                return true;
            }
            return false;
        }

        if (lecture.getMaxTeacherNum() - lecture.getCurrentTeacherNum() > 0){
            Course course = new Course(courseRegisterDTO.getLectureId(), userId,courseRegisterDTO.getAttendType());
            lecture.setCurrentTeacherNum(lecture.getCurrentTeacherNum() + 1);
            Date today = new Date();
            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
            course.setRegisterDate(date.format(today.getTime()));
            courseRepository.save(course);
            return true;
        }
        return false;

    }

    public boolean deleteCourseByCourseId(int courseId) {
        Course course = courseRepository.getCourseByCourseId(courseId);
        if (course == null){
            return false;
        }
        Lecture lecture = lectureRepository.findLectureByLectureId(course.getLectureId());
        if (course.getAttendType() == 0){
            lecture.setCurrentStudentNum(lecture.getCurrentStudentNum() - 1);
            lectureRepository.saveAndFlush(lecture);
        }else{
            lecture.setCurrentTeacherNum(lecture.getCurrentTeacherNum() - 1);
            lectureRepository.saveAndFlush(lecture);
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
