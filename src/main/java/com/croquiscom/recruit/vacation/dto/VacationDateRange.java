package com.croquiscom.recruit.vacation.dto;

import lombok.Getter;
import org.apache.tomcat.jni.Local;

import java.time.LocalDate;

@Getter
public class VacationDateRange {

    private final LocalDate startDate;
    private final LocalDate endDate;

    public VacationDateRange(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public VacationDateRange(VacationRequest vacationRequest) {
        startDate = vacationRequest.getVacationStartDate();
        endDate = vacationRequest.getVacationEndDate();
    }

    public VacationDateRange(VacationResponse vacationResponse) {
        startDate = vacationResponse.getVacationStartDate();
        endDate = vacationResponse.getVacationEndDate();
    }

}
