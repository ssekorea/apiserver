package com.wellness.sseproject.web.controller.message;

import com.wellness.sseproject.domain.Feed;
import com.wellness.sseproject.web.controller.dto.FeedDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedResponseMessage extends ApiResponseMessage {

    private FeedDTO feed;

    public FeedResponseMessage(FeedDTO feed) {
        this.feed = feed;
        this.setHttpStatusCodeOK();
    }
}
