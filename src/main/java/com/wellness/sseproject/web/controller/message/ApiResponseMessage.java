package com.wellness.sseproject.web.controller.message;


import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ApiResponseMessage {
    private String statusCode;

    public ApiResponseMessage(){

    }

    public ApiResponseMessage(String errorMessage, String statusCode) {

        this.statusCode = statusCode;
    }

    public void setHttpStatusCodeOK(){
        this.statusCode = HttpStatus.OK.toString();
    }


}
