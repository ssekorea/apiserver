package com.wellness.sseproject.web.controller.message;

import com.wellness.sseproject.web.controller.dto.UserModifyDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserModifyResponseMessage extends ApiResponseMessage {

    UserModifyDTO userModifyDTO;

    public UserModifyResponseMessage(UserModifyDTO userModifyDTO) {
        this.userModifyDTO = userModifyDTO;
    }

    public UserModifyResponseMessage(String errorMessage, String statusCode, UserModifyDTO userModifyDTO) {
        super(errorMessage, statusCode);
        this.userModifyDTO = userModifyDTO;
    }
}
