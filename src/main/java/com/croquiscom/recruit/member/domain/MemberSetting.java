package com.croquiscom.recruit.member.domain;

import com.croquiscom.recruit.common.BaseEntity;
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
    private Integer remainingVacationDays;

}
