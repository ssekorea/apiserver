package com.wellness.sseproject.web.controller;


import com.wellness.sseproject.application.LectureApplicationService;
import com.wellness.sseproject.application.LectureQueryService;
import com.wellness.sseproject.web.controller.dto.LectureDTO;
import com.wellness.sseproject.web.controller.dto.LectureImageDTO;
import com.wellness.sseproject.web.controller.dto.LectureModifyDTO;
import com.wellness.sseproject.web.controller.dto.LectureRegisterDTO;
import com.wellness.sseproject.web.controller.message.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/lectures")
public class LectureController {

    @Autowired
    LectureQueryService lectureQueryService;

    @Autowired
    LectureApplicationService lectureApplicationService;

    //TEST_DONE
    @RequestMapping(value = "/{lectureId}", method = RequestMethod.GET)
    public ResponseEntity<ApiResponseMessage> getLectureById(@PathVariable int lectureId) {

        LectureDTO lectureDTO = lectureQueryService.getLectureByIdIgnoreMasking(lectureId);
        if (lectureDTO == null) {
            return new ResponseEntity<>(ErrorResponseMessageFactory.createErrorResponseMessageFactory("Invalid lecture Id", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
        ApiResponseMessage apiResponseMessage = new LectureResponseMessage(lectureDTO);
        return new ResponseEntity<>(apiResponseMessage, HttpStatus.OK);
    }

    //TEST DONE
    // 기간에 따라 보이는거 안보이는거 설정.. all 이 전체
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ApiResponseMessage> getLectureList(@RequestParam(defaultValue = "0") int startIndex,
                                                             @RequestParam(defaultValue = "30") int count,
                                                             @RequestParam(defaultValue = "current") String dateOption,
                                                             @RequestParam(defaultValue = "") String lectureType) {
        if (lectureType.length() == 0) {
            List<LectureDTO> lectureDTOList = lectureQueryService.getLectureDTOListByPage(startIndex, count, dateOption);
            if (lectureDTOList == null){
                return new ResponseEntity<>(ErrorResponseMessageFactory.createErrorResponseMessageFactory("There is no lecture", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
            }

            ApiResponseMessage apiResponseMessage = new LectureListResponseMessage(lectureDTOList);
            return new ResponseEntity<>(apiResponseMessage, HttpStatus.OK);
        }
        List<LectureDTO> lectureDTOList = lectureQueryService.getLectureDTOListByPageAndType(startIndex, count, lectureType, dateOption);
        if (lectureDTOList == null) {
            return new ResponseEntity<>(ErrorResponseMessageFactory.createErrorResponseMessageFactory("There is no lecture", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }

        ApiResponseMessage apiResponseMessage = new LectureListResponseMessage(lectureDTOList);
        return new ResponseEntity<>(apiResponseMessage, HttpStatus.OK);
    }

    //Test_DONE
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ApiResponseMessage> registerLecture(@RequestBody LectureRegisterDTO lectureRegisterDTO) {
        ApiResponseMessage apiResponseMessage = new LectureResponseMessage(lectureApplicationService.registerLecture(lectureRegisterDTO));
        return new ResponseEntity<>(apiResponseMessage, HttpStatus.OK);
    }

    //Test_DONE
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{lectureId}", method = RequestMethod.DELETE)
    public ResponseEntity<ApiResponseMessage> deleteLecture(@PathVariable int lectureId) {
        if (lectureQueryService.checkValidLectureId(lectureId)) {
            return new ResponseEntity<>(ErrorResponseMessageFactory.createErrorResponseMessageFactory("Invalid lecture Id", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }

        lectureApplicationService.deleteLecture(lectureId);
        ApiResponseMessage apiResponseMessage = new ApiResponseMessage();
        apiResponseMessage.setHttpStatusCodeOK();
        return new ResponseEntity<>(apiResponseMessage, HttpStatus.OK);
    }

    //Test_DONE
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{lectureId}", method = RequestMethod.PUT)
    public ResponseEntity<ApiResponseMessage> modifyLecture(@PathVariable int lectureId, @RequestBody LectureModifyDTO lectureModifyDTO) {

        if (lectureQueryService.getLectureById(lectureId) == null) {
            return new ResponseEntity<>(ErrorResponseMessageFactory.createErrorResponseMessageFactory("Invalid lecture Id", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
        lectureModifyDTO.setLectureId(lectureId);
        ApiResponseMessage apiResponseMessage = new LectureResponseMessage(lectureApplicationService.modifyLecture(lectureModifyDTO));
        return new ResponseEntity<>(apiResponseMessage, HttpStatus.OK);
    }

    //Test_DONE
    @RequestMapping(value = "/{lectureId}/images", method = RequestMethod.GET)
    public ResponseEntity<ApiResponseMessage> getLectureImageUrlsById(@PathVariable int lectureId) {

        List<LectureImageDTO> lectureImageDTOS = lectureQueryService.getLectureImageDTOListByLectureId(lectureId);
        if (lectureImageDTOS == null) {
            return new ResponseEntity<>(ErrorResponseMessageFactory.createErrorResponseMessageFactory("Invalid lecture Id", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
        ApiResponseMessage apiResponseMessage = new LectureImagesResponseMessage(lectureImageDTOS);
        apiResponseMessage.setHttpStatusCodeOK();
        return new ResponseEntity<>(apiResponseMessage, HttpStatus.OK);
    }

    //Test_DONE
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{lectureId}/images", method = RequestMethod.DELETE)
    public ResponseEntity<ApiResponseMessage> deleteAllLectureImageByLectureId(@PathVariable int lectureId) {

        if (lectureQueryService.getLectureImageDTOListByLectureId(lectureId).size() == 0) {
            return new ResponseEntity<>(ErrorResponseMessageFactory.createErrorResponseMessageFactory("Invalid lecture Id", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
        lectureApplicationService.deleteLectureImage(lectureId);
        ApiResponseMessage apiResponseMessage = new ApiResponseMessage();
        apiResponseMessage.setHttpStatusCodeOK();
        return new ResponseEntity<>(apiResponseMessage, HttpStatus.OK);
    }

}
