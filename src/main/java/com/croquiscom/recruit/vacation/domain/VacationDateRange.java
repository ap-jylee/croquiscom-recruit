package com.croquiscom.recruit.vacation.domain;

import com.croquiscom.recruit.vacation.dto.VacationRequest;
import com.croquiscom.recruit.vacation.dto.VacationResponse;
import lombok.Getter;

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
