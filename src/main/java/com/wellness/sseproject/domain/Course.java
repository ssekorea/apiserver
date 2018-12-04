package com.wellness.sseproject.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "COURSE_TB")
@Data
public class Course {

    @Id
    @Column(name = "course_id")
    int courseId;
    @Column(name = "user_id")
    String userId;
    @Column(name = "lecture_id")
    int lectureId;
    @Column(name = "register_date")
    String registerDate;
    @Column(name = "attend_flag")
    int attendFlag;
    @Column(name = "completion_flag")
    int completionFlag;
    @Column(name = "attend_type")
    int attendType;
    @Column(name = "payment_info")
    int paymentInfo;
    @Column(name = "is_payment_confirm")
    boolean isPaymentConfirm;

    public Course(int lectureId, String userId, int attendType) {
        this.lectureId = lectureId;
        this.userId = userId;
        this.attendType = attendType;
    }
}
