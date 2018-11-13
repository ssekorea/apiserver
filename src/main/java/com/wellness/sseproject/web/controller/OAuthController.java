package com.wellness.sseproject.web.controller;

import com.wellness.sseproject.application.UserApplicationService;
import com.wellness.sseproject.application.UserQueryService;
import com.wellness.sseproject.util.JwtTokenUtil;
import com.wellness.sseproject.web.controller.dto.ThirdPartyLoginDTO;
import com.wellness.sseproject.web.controller.dto.LoginDTO;
import com.wellness.sseproject.web.controller.dto.UserInfoDTO;
import com.wellness.sseproject.web.controller.message.ApiResponseMessage;
import com.wellness.sseproject.web.controller.message.ErrorResponseMessageFactory;
import com.wellness.sseproject.web.controller.message.UserResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/oauth")
public class OAuthController {

    @Autowired
    UserApplicationService applicationService;
    @Autowired
    UserQueryService queryService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ApiResponseMessage> generalUserLogin(@RequestBody LoginDTO loginDTO) {

        if (!queryService.checkValidLogin(loginDTO.getUserId(), loginDTO.getPassword())) {
            return new ResponseEntity<>(ErrorResponseMessageFactory.
                    createErrorResponseMessageFactory("Invalid User Login", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
        UserInfoDTO userInfoDTO = queryService.getUserInfoDTOById(loginDTO.getUserId());
        String token = jwtTokenUtil.generateToken(userInfoDTO);
        userInfoDTO.setToken(token);

        ApiResponseMessage apiResponseMessage = new UserResponseMessage(userInfoDTO);
        return new ResponseEntity<>(apiResponseMessage, HttpStatus.OK);
    }

    @RequestMapping(value = "/third_party", method = RequestMethod.POST)
    public ResponseEntity<ApiResponseMessage> thirdPartyLogin(@RequestBody ThirdPartyLoginDTO thirdPartyLoginDTO) {
        // id 랑 토큰 줌..
        // 대충 Validate 하고 JWT Token 주면되려나...
        UserInfoDTO userInfoDTO = queryService.getUserInfoDTOById(thirdPartyLoginDTO.getUserId());
        if (userInfoDTO != null && thirdPartyLoginDTO.getAccessToken().length() > 15) {
            String token = jwtTokenUtil.generateToken(userInfoDTO);
            userInfoDTO.setToken(token);

            ApiResponseMessage apiResponseMessage = new UserResponseMessage(userInfoDTO);
            return new ResponseEntity<>(apiResponseMessage, HttpStatus.OK);
        }
        return new ResponseEntity<>(ErrorResponseMessageFactory
                .createErrorResponseMessageFactory("Invalid third party Login", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

}
