package com.wellness.sseproject.domain.repository;


import com.wellness.sseproject.domain.LectureImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectureImageRepository extends JpaRepository<LectureImage, Integer> {

    @Query(value = "SELECT * FROM LECTURE_IMAGE_TB WHERE lecture_id = :lectureId", nativeQuery = true)
    List<LectureImage> getLectureImagesByLectureId(@Param("lectureId")int lectureId);

    LectureImage findLectureImageByIdAndLecture_LectureId(int id, int lectureId);

    @Query(value = "SELECT * FROM LECTURE_IMAGE_TB WHERE lecture_id IN :lectureIdList ORDER BY lecture_id", nativeQuery = true)
    List<LectureImage> getLectureImagesByLectureIds(@Param(value = "lectureIdList") List<Integer> lectureIdList);

    @Query(value = "DELETE FROM LECTURE_IMAGE_TB WHERE lecture_id = :lectureId", nativeQuery = true)
    LectureImage deleteLectureImageByLectureId(@Param("lectureId")int lectureId);


}
