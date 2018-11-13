package com.wellness.sseproject.web.controller.message;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponseMessage extends ApiResponseMessage {

    String errorMessage;

    public ErrorResponseMessage(){

    }

    public ErrorResponseMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ErrorResponseMessage(String errorMessage, String statusCode) {
        super(errorMessage, statusCode);
        this.errorMessage = errorMessage;
    }
}
