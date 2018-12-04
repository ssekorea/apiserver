package com.wellness.sseproject.web.controller.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class CourseModifyDTO {

    int courseId;
    int attendFlag;
    int completionFlag;
    int attendType;
    int paymentInfo;
    boolean isPaymentConfirm;
}
