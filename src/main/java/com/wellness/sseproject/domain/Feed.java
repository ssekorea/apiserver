package com.wellness.sseproject.domain;


import com.wellness.sseproject.web.controller.dto.FeedRegisterDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Data
@Table(name = "FEED_TB")
public class Feed {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "contents")
    private String contents;

    @Column(name = "register_date")
    private String registerDate;


    public Feed(FeedRegisterDTO feedRegisterDTO){
        this.title = feedRegisterDTO.getTitle();
        this.contents = feedRegisterDTO.getContents();
        this.registerDate = feedRegisterDTO.getRegisterDate();
    }
}
