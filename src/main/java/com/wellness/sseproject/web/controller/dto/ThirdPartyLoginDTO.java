package com.wellness.sseproject.web.controller.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ThirdPartyLoginDTO {

    private String userId;
    private String accessToken;

    public ThirdPartyLoginDTO(String userId, String accessToken) {
        this.userId = userId;
        this.accessToken = accessToken;
    }
}
