package com.wellness.sseproject.web.controller.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Data
public class FeedRegisterDTO {

    private String title;
    private String contents;
    private String registerDate;

    private List<String> imageUrls = new ArrayList<>();

}
