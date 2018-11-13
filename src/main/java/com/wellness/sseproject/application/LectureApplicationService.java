package com.wellness.sseproject.application;

import com.wellness.sseproject.domain.Lecture;
import com.wellness.sseproject.domain.LectureImage;
import com.wellness.sseproject.domain.repository.LectureImageRepository;
import com.wellness.sseproject.domain.repository.LectureRepository;
import com.wellness.sseproject.web.controller.dto.LectureDTO;
import com.wellness.sseproject.web.controller.dto.LectureModifyDTO;
import com.wellness.sseproject.web.controller.dto.LectureRegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class LectureApplicationService {

    @Autowired
    LectureRepository lectureRepository;

    @Autowired
    LectureImageRepository lectureImageRepository;


    @Transactional
    public LectureDTO modifyLecture(LectureModifyDTO lectureModifyDTO) {

        Lecture lecture = new Lecture(lectureModifyDTO);
        lectureRepository.save(lecture);

        List<LectureImage> lectureImages = new ArrayList<>();

        for (String lectureImageUrl : lectureModifyDTO.getLectureImageUrls()){
            LectureImage tempLectureImage = new LectureImage();
            tempLectureImage.setImageUrl(lectureImageUrl);
            tempLectureImage.setLecture(lecture);
            lectureImages.add(tempLectureImage);
        }

        List<LectureImage> tempLectureImages = lectureImageRepository.getLectureImagesByLectureId(lecture.getLectureId());
        for (LectureImage lectureImage : tempLectureImages){
            lectureImageRepository.delete(lectureImage);
        }
        lectureImageRepository.saveAll(lectureImages);

        return new LectureDTO(lectureModifyDTO);
    }


    public LectureDTO registerLecture(LectureRegisterDTO lectureRegisterDTO) {
        Lecture lecture = new Lecture(lectureRegisterDTO);
        lecture.setMasking(true);
        Lecture savedLecture = lectureRepository.saveAndFlush(lecture);
        List<LectureImage> lectureImages = new ArrayList<>();

        for (String imageUrl : lectureRegisterDTO.getLectureImageUrls()){
            LectureImage lectureImage = new LectureImage();
            lectureImage.setLecture(savedLecture);
            lectureImage.setImageUrl(imageUrl);
            lectureImages.add(lectureImage);
        }
        lectureImageRepository.saveAll(lectureImages);

        return new LectureDTO(savedLecture.getLectureId(), lectureRegisterDTO);
    }


    public void deleteLecture(int lectureId) {

        Lecture lecture = lectureRepository.findLectureByLectureIdAndMasking(lectureId, true);
        lecture.setMasking(false);
        lectureRepository.saveAndFlush(lecture);

        deleteLectureImage(lectureId);
    }


    public boolean deleteLectureImage(int lectureId){
        Lecture lecture = lectureRepository.findLectureByLectureIdAndMasking(lectureId, true);
        List<LectureImage> lectureImages = lectureImageRepository.getLectureImagesByLectureId(lectureId);
        if (lectureImages == null){
            return false;
        }
        lectureImageRepository.deleteAll(lectureImages);

        return true;
    }
}