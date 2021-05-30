package com.croquiscom.recruit.vacation.dto;

import com.croquiscom.recruit.member.domain.MemberSetting;
import com.croquiscom.recruit.vacation.domain.Vacation;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class VacationResponse {

    private Long vacationId;
    private String memberId;
    private String vacationType;
    @JsonFormat(pattern = "yyyy.MM.dd")
    private LocalDate vacationStartDate;
    @JsonFormat(pattern = "yyyy.MM.dd")
    private LocalDate vacationEndDate;
    private Integer usedDays;
    private String comment;
    private Boolean cancelYn;
    @JsonFormat(pattern = "yyyy.MM.dd hh:mm")
    private LocalDateTime createdDate;
    @JsonFormat(pattern = "yyyy.MM.dd hh:mm")
    private LocalDateTime modifiedDate;

    private Integer remainingUsedDays;

    private VacationResponse(Long vacationId, String memberId, String vacationType, LocalDate vacationStartDate, LocalDate vacationEndDate, Integer usedDays, String comment, Boolean cancelYn, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.vacationId = vacationId;
        this.memberId = memberId;
        this.vacationType = vacationType;
        this.vacationStartDate = vacationStartDate;
        this.vacationEndDate = vacationEndDate;
        this.usedDays = usedDays;
        this.comment = comment;
        this.cancelYn = cancelYn;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public static VacationResponse of(Vacation vacation) {
        return new VacationResponse(vacation.getVacationId(), vacation.getMemberId(), vacation.getVacationType(), vacation.getVacationStartDate(), vacation.getVacationEndDate(), vacation.getUsedDays(), vacation.getComment(), vacation.getCancelYn(), vacation.getCreatedDate(), vacation.getModifiedDate());
    }

    public VacationResponse setRemainingUsedDays(MemberSetting memberSetting) {
        return setRemainingUsedDays(memberSetting.getRemainingVacationDays());
    }

    public VacationResponse setRemainingUsedDays(Integer remainingUsedDays) {
        this.remainingUsedDays = remainingUsedDays;
        return this;
    }

}
