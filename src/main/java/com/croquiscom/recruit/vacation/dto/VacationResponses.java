package com.croquiscom.recruit.vacation.dto;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
public class VacationResponses {

    private final List<VacationResponse> vacationResponses;

    public void checkOverlap(VacationRequest request) {
        if (isOverlap(request)) {
            throw new IllegalArgumentException("The annual dates overlap.");
        }
    }

    private Boolean isOverlap(VacationRequest request) {
        return vacationResponses.stream().anyMatch((vacationResponse) ->
                isBetween(request.getVacationStartDate(), vacationResponse) ||
                isBetween(request.getVacationEndDate(), vacationResponse));
    }

    private Boolean isBetween(LocalDate date, VacationResponse vacationResponse) {
        LocalDate startDate = vacationResponse.getVacationStartDate();
        LocalDate endDate = vacationResponse.getVacationEndDate();
        if (date.isEqual(startDate) || date.isEqual(endDate)) {
            return true;
        }
        return date.isAfter(startDate) && date.isBefore(endDate);
    }

}
