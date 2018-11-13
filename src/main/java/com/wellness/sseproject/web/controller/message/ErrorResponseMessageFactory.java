package com.wellness.sseproject.web.controller.message;

import org.springframework.http.HttpStatus;

public class ErrorResponseMessageFactory {

    public static ApiResponseMessage createErrorResponseMessageFactory(String errorMessage, HttpStatus httpStatus){
        ApiResponseMessage apiResponseMessage = new ErrorResponseMessage();
        apiResponseMessage.setStatusCode(httpStatus.toString());
        ((ErrorResponseMessage) apiResponseMessage).setErrorMessage(errorMessage);

        return apiResponseMessage;
    }
}
