package com.wellness.sseproject.web.controller.message;

import com.wellness.sseproject.web.controller.dto.UserInfoDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseMessage extends ApiResponseMessage {

    private UserInfoDTO userInfoDTO;

    public UserResponseMessage(UserInfoDTO userInfoDTO) {
        this.userInfoDTO = userInfoDTO;
        this.setHttpStatusCodeOK();
    }
}
