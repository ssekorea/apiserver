package com.wellness.sseproject.application;

import com.wellness.sseproject.domain.Course;
import com.wellness.sseproject.domain.Lecture;
import com.wellness.sseproject.domain.LectureImage;
import com.wellness.sseproject.domain.repository.CourseRepository;
import com.wellness.sseproject.domain.repository.LectureImageRepository;
import com.wellness.sseproject.domain.repository.LectureRepository;
import com.wellness.sseproject.web.controller.dto.CourseLectureDTO;
import com.wellness.sseproject.web.controller.dto.LectureDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseQueryService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    LectureRepository lectureRepository;

    @Autowired
    LectureImageRepository lectureImageRepository;

    public List<CourseLectureDTO> getCourseLecturesByUserId(String userId){

        List<Course> courseList = courseRepository.getCourseByUserIdOrderByLectureId(userId);

        if (courseList.size() == 0){
            return null;
        }

        List<Integer> lectureIdList = new ArrayList<>();
        for (Course course : courseList){
            lectureIdList.add(course.getLectureId());
        }
        List<Lecture> lectureList = lectureRepository.getLecturesByLectureIds(lectureIdList);
        List<LectureImage> lectureImages = lectureImageRepository.getLectureImagesByLectureIds(lectureIdList);

        List<LectureDTO> lectureDTOList = new ArrayList<>();

        for (Lecture lecture : lectureList){
            LectureDTO lectureDTO = new LectureDTO(lecture);
            for (LectureImage lectureImage : lectureImages){
                if (lecture.getLectureId() == lectureImage.getId()){
                    lectureDTO.getLectureImageUrls().add(lectureImage.getImageUrl());
                }
            }
            lectureDTOList.add(lectureDTO);
        }
        List<CourseLectureDTO> courseLectureDTOList = new ArrayList<>();

        for (int i = 0; i < courseList.size(); i++){
            courseLectureDTOList.add(new CourseLectureDTO(courseList.get(i), lectureDTOList.get(i)));
        }
        return courseLectureDTOList;
    }

    public CourseLectureDTO getCourseLectureByUserIdAndCourseId(String userId, int courseId){

        Course course = courseRepository.getCourseByUserIdAndCourseId(userId, courseId);
        if (course == null){
            return null;
        }
        Lecture lecture = lectureRepository.findLectureByLectureIdAndMasking(course.getLectureId(), true);
        List<LectureImage> lectureImages = lectureImageRepository.getLectureImagesByLectureId(course.getLectureId());

        LectureDTO lectureDTO = new LectureDTO(lecture);
        lectureDTO.setLectureImageUrls(lectureImages);
        return new CourseLectureDTO(course, lectureDTO);

    }

    public CourseLectureDTO getCourseLectureByCourseId(int courseId){
        Course course = courseRepository.getCourseByCourseId(courseId);
        if (course == null){
            return null;
        }
        Lecture lecture = lectureRepository.findLectureByLectureIdAndMasking(course.getLectureId(), true);
        List<LectureImage> lectureImages = lectureImageRepository.getLectureImagesByLectureId(course.getLectureId());

        LectureDTO lectureDTO = new LectureDTO(lecture);
        lectureDTO.setLectureImageUrls(lectureImages);
        return new CourseLectureDTO(course, lectureDTO);
    }




}
