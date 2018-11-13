package com.wellness.sseproject.application;

import com.wellness.sseproject.domain.Feed;
import com.wellness.sseproject.domain.FeedImage;
import com.wellness.sseproject.domain.repository.FeedImageRepository;
import com.wellness.sseproject.domain.repository.FeedRepository;
import com.wellness.sseproject.web.controller.dto.FeedDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FeedQueryService {

    @Autowired
    FeedRepository feedRepository;

    @Autowired
    FeedImageRepository feedImageRepository;

    public FeedDTO getFeed(int feedId){

        Feed feed = feedRepository.findFeedById(feedId);
        if (feed == null){
            return null;
        }
        List<FeedImage> feedImageList = feedImageRepository.getFeedImagesByFeedId(feedId);

        FeedDTO feedDTO = new FeedDTO(feed, feedImageList);

        return feedDTO;
    }

    public List<FeedDTO> getFeedListByPage(int startIndex, int count){

        int startCount = startIndex * count;

        List<Feed> feedList = feedRepository.findFeedsByPage(startCount, count);
        if (feedList.size() == 0){
            return null;
        }
        List<Integer> feedIdList = new ArrayList<>();
        for (Feed feed : feedList){
            feedIdList.add(feed.getId());
        }
        List<FeedDTO> feedDTOList = new ArrayList<>();
        List<FeedImage> feedImageList = feedImageRepository.getFeedImagesByFeedIdList(feedIdList);


        for (int i = 0; i < feedList.size(); i++){
            FeedDTO feedDTO = new FeedDTO(feedList.get(i));
            for (int j = 0; j < feedImageList.size(); j++){
                if (feedList.get(i).getId() == feedImageList.get(j).getId()){
                    feedDTO.addFeedImageUrls(feedImageList.get(j));
                }
            }
            feedDTOList.add(feedDTO);
        }


        return feedDTOList;
    }
}
