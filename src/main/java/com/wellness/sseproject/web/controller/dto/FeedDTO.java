package com.wellness.sseproject.web.controller.dto;

import com.wellness.sseproject.domain.Feed;
import com.wellness.sseproject.domain.FeedImage;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Data
public class FeedDTO  {

    private int id;

    private String title;

    private String contents;

    private String registerDate;

    private List<String> feedImageUrls = new ArrayList<>();

    public FeedDTO(Feed feed){
        this.id = feed.getId();
        this.title = feed.getTitle();
        this.contents = feed.getContents();
        this.registerDate = feed.getRegisterDate();
    }

    public FeedDTO(Feed feed, List<FeedImage> feedImageList){
        this.id = feed.getId();
        this.title = feed.getTitle();
        this.contents = feed.getRegisterDate();
        this.registerDate = feed.getRegisterDate();

        for (FeedImage feedImage: feedImageList){
            feedImageUrls.add(feedImage.getImageUrl());
        }
    }

    public void addFeedImageUrls(FeedImage feedImage){
        feedImageUrls.add(feedImage.getImageUrl());
    }


}
