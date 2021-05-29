package com.croquiscom.recruit.vacation.dto;

import com.croquiscom.recruit.vacation.domain.Vacation;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.Authentication;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class VacationRequest {

    private String memberId;
    private String vacationType;
    @JsonFormat(pattern = "yyyyMMdd")
    private LocalDate vacationStartDate;
    @JsonFormat(pattern = "yyyyMMdd")
    private LocalDate vacationEndDate;
    private Integer usedDays;
    private String comment;

    public Vacation toVacation() {
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

    public VacationRequest setMemberIdFromAuthentication(Authentication authentication) {
        memberId = authentication.getName();
        return this;
    }

}
