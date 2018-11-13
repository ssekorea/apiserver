package com.wellness.sseproject.domain.repository;

import com.wellness.sseproject.domain.Feed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedRepository extends JpaRepository<Feed, Integer> {


    Feed findFeedById(int feedId);

    @Query(value = "SELECT * FROM FEED_TB ORDER BY register_date LIMIT :startIndex, :pageCount", nativeQuery = true)
    List<Feed> findFeedsByPage(@Param("startIndex")int startIndex, @Param("pageCount")int pageCount);

}
