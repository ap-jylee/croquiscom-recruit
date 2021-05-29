package com.croquiscom.recruit.vacation.domain;

import com.croquiscom.recruit.common.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "vacation")
@Getter
@NoArgsConstructor
public class Vacation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vacation_id")
    private Long vacationId;
    @Column(name = "member_id")
    private String memberId;
    @Column(name = "vacation_type")
    private String vacationType;
    @Column(name = "vacation_start_date")
    private LocalDate vacationStartDate;
    @Column(name = "vacation_end_date")
    private LocalDate vacationEndDate;
    @Column(name = "used_days")
    private Integer usedDays;
    @Column(name = "comment")
    private String comment;
    @Column(name = "cancel_yn")
    private Boolean cancelYn;

    @Builder
    public Vacation(Long vacationId, String memberId, String vacationType, LocalDate vacationStartDate, LocalDate vacationEndDate, Integer usedDays, String comment, Boolean cancelYn) {
        this.vacationId = vacationId;
        this.memberId = memberId;
        this.vacationType = vacationType;
        this.vacationStartDate = vacationStartDate;
        this.vacationEndDate = vacationEndDate;
        this.usedDays = usedDays;
        this.comment = comment;
        this.cancelYn = cancelYn;
    }

}
