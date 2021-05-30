package com.croquiscom.recruit.member.domain;

import com.croquiscom.recruit.config.MemberSettingConfig;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@Setter
public class MemberMock {

    private String id;
    private String password;
    private Double remainingVacationDays;

    private MemberMock() {

    }

    public static MemberMock of(Member member) {
        MemberMock mock = new MemberMock();
        mock.setId(member.getId());
        mock.setPassword(member.getPassword());
        mock.setRemainingVacationDays(member.getMemberSetting().getRemainingVacationDays());
        return mock;
    }

    public Boolean isEqualMember(String id) {
        return StringUtils.equals(this.id, id);
    }

    public Boolean isFullRemainingVacationDays() {
        return remainingVacationDays.equals(MemberSettingConfig.TOTAL_VACATION_DAYS);
    }

}
