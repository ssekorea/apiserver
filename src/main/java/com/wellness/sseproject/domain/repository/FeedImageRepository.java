package com.wellness.sseproject.domain.repository;

import com.wellness.sseproject.domain.FeedImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedImageRepository extends JpaRepository<FeedImage, Integer> {

    @Query(value = "SELECT * FROM FEED_IMAGE_TB WHERE feed_id = :feedId", nativeQuery = true)
    List<FeedImage> getFeedImagesByFeedId(@Param("feedId")int feedId);

    @Query(value = "SELECT * FROM FEED_IMAGE_TB WHERE feed_id in :feedIdList", nativeQuery = true)
    List<FeedImage> getFeedImagesByFeedIdList(@Param("feedIdList")List<Integer> feedIdList);
}
