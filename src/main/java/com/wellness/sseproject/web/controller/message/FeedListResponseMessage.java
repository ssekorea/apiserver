package com.wellness.sseproject.web.controller.message;


import com.wellness.sseproject.domain.Feed;
import com.wellness.sseproject.web.controller.dto.FeedDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FeedListResponseMessage extends ApiResponseMessage {

    private List<FeedDTO> feedList;

    public FeedListResponseMessage(List<FeedDTO> feedList) {
        this.feedList = feedList;
        this.setHttpStatusCodeOK();
    }
}
