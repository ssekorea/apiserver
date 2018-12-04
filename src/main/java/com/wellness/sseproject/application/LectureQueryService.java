package com.wellness.sseproject.application;

import com.wellness.sseproject.domain.Lecture;
import com.wellness.sseproject.domain.LectureImage;
import com.wellness.sseproject.domain.repository.CourseRepository;
import com.wellness.sseproject.domain.repository.LectureImageRepository;
import com.wellness.sseproject.domain.repository.LectureRepository;
import com.wellness.sseproject.web.controller.dto.LectureDTO;
import com.wellness.sseproject.web.controller.dto.LectureImageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LectureQueryService {

    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private LectureImageRepository lectureImageRepository;

    @Autowired
    private CourseRepository courseRepository;


    public List<LectureDTO> getLectureDTOListByPageAndType(int startIndex, int count, String type, String dateOption) {

        int startCount = count * startIndex;
        List<Lecture> lectureList;
        if (dateOption.equals("current")) {
            Date today = new Date();
            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
            lectureList = lectureRepository.findLectureListByLectureTypeAndDate(type, date.format(today), startCount, count);
        } else {
            lectureList = lectureRepository.findLectureListByLectureType(type, startCount, count);
        }

        if (lectureList.size() == 0) {
            return null;
        }

        List<Integer> lectureIdList = new ArrayList<>();
        List<LectureDTO> lectureDTOList = new ArrayList<>();

        for (Lecture lecture : lectureList) {
            lectureIdList.add(lecture.getLectureId());
            lectureDTOList.add(new LectureDTO(lecture));
        }
        List<LectureImage> lectureImageList = lectureImageRepository.getLectureImagesByLectureIds(lectureIdList);

        for (LectureDTO lectureDTO : lectureDTOList) {
            for (LectureImage lectureImage : lectureImageList) {
                if (lectureImage.getLecture().getLectureId() == lectureDTO.getLectureId()) {
                    lectureDTO.getLectureImageUrls().add(lectureImage.getImageUrl());
                }
            }
        }

        return lectureDTOList;
    }


    public List<LectureDTO> getLectureDTOListByPage(int startIndex, int count, String dateOption) {
        int startCount = count * startIndex;
        List<Lecture> lectureList;
        if (dateOption.equals("current")) {
            Date today = new Date();
            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
            lectureList = lectureRepository.findLectureByAllLectureTypeAndDate(date.format(today), startCount, count);
        } else {
            lectureList = lectureRepository.findLectureByAllLectureType(startCount, count);
        }

        if (lectureList == null) {
            return null;
        }

        List<Integer> lectureIdList = new ArrayList<>();
        List<LectureDTO> lectureDTOList = new ArrayList<>();

        for (Lecture lecture : lectureList) {
            lectureIdList.add(lecture.getLectureId());
            lectureDTOList.add(new LectureDTO(lecture));
        }
        List<LectureImage> lectureImageList = lectureImageRepository.getLectureImagesByLectureIds(lectureIdList);


        for (LectureDTO lectureDTO : lectureDTOList) {
            for (LectureImage lectureImage : lectureImageList) {
                if (lectureImage.getLecture().getLectureId() == lectureDTO.getLectureId()) {
                    lectureDTO.getLectureImageUrls().add(lectureImage.getImageUrl());
                }
            }
        }

        return lectureDTOList;
    }


    public boolean checkValidLectureId(int lectureId) {
        System.out.println(lectureId);
        System.out.println(lectureRepository.countByLectureId(lectureId));
        return lectureRepository.countByLectureId(lectureId) == 0;
    }

    public List<LectureImageDTO> getLectureImageDTOListByLectureId(int lectureId) {
        Lecture lecture = lectureRepository.findLectureByLectureIdAndMasking(lectureId, true);
        if(lecture == null){
            return null;
        }
        List<LectureImage> lectureImages = lectureImageRepository.getLectureImagesByLectureId(lectureId);
        if (lectureImages.size() == 0) {
            return null;
        }
        List<LectureImageDTO> lectureImageDTOList = new ArrayList<>();
        for (LectureImage lectureImage : lectureImages) {
            lectureImageDTOList.add(new LectureImageDTO(lectureImage));
        }
        return lectureImageDTOList;
    }


    public LectureDTO getLectureById(int lectureId) {
        Lecture lecture = lectureRepository.findLectureByLectureIdAndMasking(lectureId, true);
        if (lecture == null) {
            return null;
        }
        System.out.println(lectureId);
        List<LectureImage> lectureImageList = lectureImageRepository.getLectureImagesByLectureId(lectureId);
        LectureDTO lectureDTO = new LectureDTO(lecture);
        lectureDTO.setLectureImageUrls(lectureImageList);

        return lectureDTO;
    }

    public LectureDTO getLectureByIdIgnoreMasking(int lectureId){

        Lecture lecture = lectureRepository.findLectureByLectureId(lectureId);
        if (lecture == null) {
            return null;
        }
        System.out.println(lectureId);
        List<LectureImage> lectureImageList = lectureImageRepository.getLectureImagesByLectureId(lectureId);
        LectureDTO lectureDTO = new LectureDTO(lecture);
        lectureDTO.setLectureImageUrls(lectureImageList);

        return lectureDTO;
    }
}
