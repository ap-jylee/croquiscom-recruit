package com.croquiscom.recruit.vacation.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VacationMock {

    private Long vacationId;
    private String memberId;
    private String vacationType;
    private String vacationStartDate;
    private String vacationEndDate;
    private Integer usedDays;
    private String comment;
    private Boolean cancelYn;
    private String createdDate;
    private String modifiedDate;
    private Double remainingUsedDays;

}
