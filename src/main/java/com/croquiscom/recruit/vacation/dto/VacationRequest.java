package com.croquiscom.recruit.vacation.dto;

import com.croquiscom.recruit.vacation.domain.Vacation;
import com.croquiscom.recruit.vacation.domain.VacationType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class VacationRequest {

    @NotNull(message = "vacation type is required")
    private VacationType vacationType;
    @NotNull(message = "vacation start date is required")
    @JsonFormat(pattern = "yyyyMMdd")
    private LocalDate vacationStartDate;
    @NotNull(message = "vacation end date is required")
    @JsonFormat(pattern = "yyyyMMdd")
    private LocalDate vacationEndDate;
    @NotNull(message = "used days is required")
    private Double usedDays;
    private String comment;

    public Vacation toVacation(String memberId) {
        return Vacation.builder()
                .memberId(memberId)
                .vacationType(vacationType)
                .vacationStartDate(vacationStartDate)
                .vacationEndDate(vacationEndDate)
                .usedDays(usedDays)
                .comment(comment)
                .cancelYn(false)
                .build();
    }

}
