package com.wellness.sseproject.domain.repository;

import com.wellness.sseproject.domain.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Integer> {

    @Query(value = "SELECT * FROM LECTURE_TB WHERE masking = 1 AND lecture_type = (:lectureType) AND start_time >= (:startTime) ORDER BY start_time LIMIT :startIndex, :pageCount", nativeQuery = true)
    List<Lecture> findLectureListByLectureTypeAndDate(@Param("lectureType") String lectureType,
                                               @Param("startTime") String startTime,
                                               @Param("startIndex") int startIndex,
                                               @Param("pageCount") int pageCount);

    @Query(value = "SELECT * FROM LECTURE_TB WHERE masking = 1 AND lecture_type = (:lectureType) ORDER BY start_time LIMIT :startIndex, :pageCount", nativeQuery = true)
    List<Lecture> findLectureListByLectureType(@Param("lectureType") String lectureType,
                                                      @Param("startIndex") int startIndex,
                                                      @Param("pageCount") int pageCount);

    @Query(value = "SELECT * FROM LECTURE_TB WHERE masking = 1 AND start_time >= (:startTime) ORDER BY start_time LIMIT :startIndex, :pageCount", nativeQuery = true)
    List<Lecture> findLectureByAllLectureTypeAndDate(@Param("startTime") String startTime,
                                                     @Param("startIndex") int startIndex,
                                                     @Param("pageCount") int pageCount);

    @Query(value = "SELECT * FROM LECTURE_TB WHERE masking = 1 ORDER BY start_time LIMIT :startIndex, :pageCount", nativeQuery = true)
    List<Lecture> findLectureByAllLectureType(@Param("startIndex") int startIndex,
                                          @Param("pageCount") int pageCount);


    @Query(value = "SELECT * FROM LECTURE_TB WHERE masking = 1 AND lecture_id in :lectureIdList ORDER BY lecture_id", nativeQuery = true)
    List<Lecture> getLecturesByLectureIds(@Param("lectureIdList") List<Integer> lectureIdList);

    Lecture findLectureByLectureIdAndMasking(int lectureId, boolean masking);

    int countByLectureId(int lectureId);


    //TODO 쿼리추가 userId로 Course 조회 후 해당 id 로 lecture 불러오는

}
