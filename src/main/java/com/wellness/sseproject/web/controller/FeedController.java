package com.wellness.sseproject.web.controller;


import com.wellness.sseproject.application.FeedApplicationService;
import com.wellness.sseproject.application.FeedQueryService;
import com.wellness.sseproject.domain.Feed;
import com.wellness.sseproject.web.controller.dto.FeedDTO;
import com.wellness.sseproject.web.controller.dto.FeedModifyDTO;
import com.wellness.sseproject.web.controller.dto.FeedRegisterDTO;
import com.wellness.sseproject.web.controller.message.ApiResponseMessage;
import com.wellness.sseproject.web.controller.message.ErrorResponseMessageFactory;
import com.wellness.sseproject.web.controller.message.FeedListResponseMessage;
import com.wellness.sseproject.web.controller.message.FeedResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/feeds")
public class FeedController{

    @Autowired
    FeedApplicationService feedApplicationService;

    @Autowired
    FeedQueryService feedQueryService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ApiResponseMessage> getFeedListByPage(@RequestParam(defaultValue = "0")int startIndex, @RequestParam(defaultValue = "5")int count){
        List<FeedDTO> feedList = feedQueryService.getFeedListByPage(startIndex, count);
        if(feedList == null){
           return new ResponseEntity<>(ErrorResponseMessageFactory.createErrorResponseMessageFactory("Invalid feed Page", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }

        ApiResponseMessage apiResponseMessage = new FeedListResponseMessage(feedList);
        return new ResponseEntity<>(apiResponseMessage, HttpStatus.OK);
    }

    @RequestMapping(value = "/{feedId}", method = RequestMethod.GET)
    public ResponseEntity<ApiResponseMessage> getFeedById(@PathVariable int feedId){
        FeedDTO feed = feedQueryService.getFeed(feedId);

        if (feed == null){
            return new ResponseEntity<>(ErrorResponseMessageFactory.createErrorResponseMessageFactory("Invalid feed Id", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }

        ApiResponseMessage apiResponseMessage = new FeedResponseMessage(feed);
        return new ResponseEntity<>(apiResponseMessage, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ApiResponseMessage> registerFeed(@RequestBody FeedRegisterDTO feedRegisterDTO){

        FeedDTO feedDTO = feedApplicationService.registerFeed(feedRegisterDTO);
        ApiResponseMessage apiResponseMessage = new FeedResponseMessage(feedDTO);
        return new ResponseEntity<>(apiResponseMessage, HttpStatus.OK);
    }

    @RequestMapping(value = "/{feedId}", method = RequestMethod.PUT)
    public ResponseEntity<ApiResponseMessage> modifyFeed(@PathVariable int feedId, @RequestBody FeedModifyDTO feedModifyDTO){

        FeedDTO feed = feedQueryService.getFeed(feedId);

        if (feed == null){
            return new ResponseEntity<>(ErrorResponseMessageFactory.createErrorResponseMessageFactory("Invalid feed Id", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
        FeedDTO feedDTO = feedApplicationService.modifyFeed(feedId, feedModifyDTO);

        ApiResponseMessage apiResponseMessage = new FeedResponseMessage(feedDTO);
        return new ResponseEntity<>(apiResponseMessage, HttpStatus.OK);
    }

    @RequestMapping(value = "/{feedId}", method = RequestMethod.DELETE)
    public ResponseEntity<ApiResponseMessage> deleteFeed(@PathVariable int feedId){

        if (feedQueryService.getFeed(feedId) == null){
            return new ResponseEntity<>(ErrorResponseMessageFactory.createErrorResponseMessageFactory("Invalid feed Id", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
        feedApplicationService.deleteFeed(feedId);
        ApiResponseMessage apiResponseMessage = new ApiResponseMessage();
        apiResponseMessage.setHttpStatusCodeOK();
        return new ResponseEntity<>(apiResponseMessage , HttpStatus.OK);
    }

}
