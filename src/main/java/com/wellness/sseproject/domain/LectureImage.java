package com.wellness.sseproject.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(name = "LECTURE_IMAGE_TB")
public class LectureImage {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "lecture_id", nullable = false)
    @JsonIgnore
    private Lecture lecture;

    public LectureImage(){

    }

    public LectureImage(String imageUrl, Lecture lecture) {
        this.imageUrl = imageUrl;
        this.lecture = lecture;
    }
}
