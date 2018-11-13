package com.wellness.sseproject.web.controller.message;

import com.wellness.sseproject.web.controller.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class UserPushListMessage extends ApiResponseMessage {

    private List<UserDTO> userList;

    public UserPushListMessage(){

    }

    public UserPushListMessage(List<UserDTO> userList) {
        this.userList = userList;
    }

    public UserPushListMessage(String errorMessage, String statusCode, List<UserDTO> userList) {
        super(errorMessage, statusCode);
        this.userList = userList;
    }
}
