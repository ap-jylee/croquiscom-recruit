package com.croquiscom.recruit.member.domain;

import com.croquiscom.recruit.common.BaseEntity;
import com.croquiscom.recruit.vacation.domain.Vacation;
import com.croquiscom.recruit.vacation.dto.VacationRequest;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "member_setting")
@Getter
public class MemberSetting extends BaseEntity {

    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "remaining_vacation_days")
    private Double remainingVacationDays;

    public MemberSetting useVacationDays(VacationRequest vacationRequest) {
        if (remainingVacationDays < vacationRequest.getUsedDays()) {
            throw new IllegalArgumentException("The remaining annual leave is insufficient.");
        }
        remainingVacationDays -= vacationRequest.getUsedDays();
        return this;
    }

    public MemberSetting cancelVacationDays(Vacation persistVacation) {
        remainingVacationDays += persistVacation.getUsedDays();
        return this;
    }

}
