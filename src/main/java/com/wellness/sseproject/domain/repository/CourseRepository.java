package com.wellness.sseproject.domain.repository;

import com.wellness.sseproject.domain.Course;
import com.wellness.sseproject.domain.Lecture;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    int countCourseByCourseId(int courseId);

    Course getCourseByCourseId(int courseId);

    List<Course> getCourseByUserIdOrderByLectureId(String userId);

    Course getCourseByUserIdAndCourseId(String userId, int courseId);


    @Query(value = "SELECT * FROM LECTURE_TB WHERE start_time >= (:startTime) ORDER BY start_time LIMIT :startIndex, :pageCount", nativeQuery = true)
    List<Lecture> findLectureByAllLectureType(@Param("startTime") String startTime,
                                              @Param("startIndex") int startIndex,
                                              @Param("pageCount") int pageCount);

}
