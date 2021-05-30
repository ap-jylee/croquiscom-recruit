package com.croquiscom.recruit.vacation.domain;

import com.croquiscom.recruit.vacation.dto.VacationRequest;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
public class VacationDateRanges {

    private final List<VacationDateRange> vacationDateRanges;

    public void checkOverlap(VacationRequest request) {
        checkOverlap(new VacationDateRange(request));
    }

    public void checkOverlap(VacationDateRange dateRange) {
        if (isOverlap(dateRange)) {
            throw new IllegalArgumentException("The annual dates overlap.");
        }
    }

    public Boolean isOverlap(VacationDateRange dateRange) {
        return vacationDateRanges.stream().anyMatch((vacationDateRange) ->
                isBetween(dateRange.getStartDate(), vacationDateRange) ||
                isBetween(dateRange.getEndDate(), vacationDateRange));
    }

    private Boolean isBetween(LocalDate date, VacationDateRange vacationDateRange) {
        LocalDate startDate = vacationDateRange.getStartDate();
        LocalDate endDate = vacationDateRange.getEndDate();
        if (date.isEqual(startDate) || date.isEqual(endDate)) {
            return true;
        }
        return date.isAfter(startDate) && date.isBefore(endDate);
    }

}
