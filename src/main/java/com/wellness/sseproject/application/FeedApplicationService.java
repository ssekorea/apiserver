package com.wellness.sseproject.application;


import com.wellness.sseproject.domain.Feed;
import com.wellness.sseproject.domain.FeedImage;
import com.wellness.sseproject.domain.repository.FeedImageRepository;
import com.wellness.sseproject.domain.repository.FeedRepository;
import com.wellness.sseproject.web.controller.dto.FeedDTO;
import com.wellness.sseproject.web.controller.dto.FeedModifyDTO;
import com.wellness.sseproject.web.controller.dto.FeedRegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FeedApplicationService {

    @Autowired
    FeedRepository feedRepository;
    @Autowired
    FeedImageRepository feedImageRepository;

    public void deleteFeed(int feedId){
        feedRepository.deleteById(feedId);
    }

    public FeedDTO registerFeed(FeedRegisterDTO feedRegisterDTO) {
        Feed feed = new Feed(feedRegisterDTO);
        Feed savedFeed = feedRepository.saveAndFlush(feed);

        List<FeedImage> feedImageList = new ArrayList<>();

        for (int i = 0; i < feedRegisterDTO.getImageUrls().size(); i++){
            FeedImage feedImage = new FeedImage();
            feedImage.setFeed(savedFeed);
            feedImage.setImageUrl(feedRegisterDTO.getImageUrls().get(i));
            feedImageList.add(feedImage);
        }

        feedImageRepository.saveAll(feedImageList);

        return new FeedDTO(feed, feedImageList);
    }

    public FeedDTO modifyFeed(int feedId, FeedModifyDTO feedModifyDTO) {
        Feed feed = feedRepository.findFeedById(feedId);

        feed.setTitle(feedModifyDTO.getTitle());
        feed.setContents(feedModifyDTO.getContents());
        feedRepository.saveAndFlush(feed);

        List<FeedImage> feedImageList = feedImageRepository.getFeedImagesByFeedId(feedId);

        FeedDTO feedDTO = new FeedDTO(feed);
        List<String> imageUrls = new ArrayList<>();
        for (int i = 0; i < feedImageList.size(); i++){
            imageUrls.add(feedImageList.get(i).getImageUrl());
        }
        feedDTO.setFeedImageUrls(imageUrls);

        return feedDTO;
    }
}
