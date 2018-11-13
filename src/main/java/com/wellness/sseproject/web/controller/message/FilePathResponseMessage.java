package com.wellness.sseproject.web.controller.message;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilePathResponseMessage extends ApiResponseMessage{

    private String filePath;

    public FilePathResponseMessage(String filePath) {
        this.filePath = filePath;
        this.setHttpStatusCodeOK();
    }

    @Override
    public String getStatusCode() {
        return super.getStatusCode();
    }


}
