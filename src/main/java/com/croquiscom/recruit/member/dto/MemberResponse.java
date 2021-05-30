package com.croquiscom.recruit.member.dto;

import com.croquiscom.recruit.member.domain.Member;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MemberResponse {

    private String id;
    private String password;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    private Integer remainingVacationDays;

    private MemberResponse(String id, String password, LocalDateTime createdDate, LocalDateTime modifiedDate, Integer remainingVacationDays) {
        this.id = id;
        this.password = password;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.remainingVacationDays = remainingVacationDays;
    }

    public static MemberResponse of(Member member) {
        return new MemberResponse(member.getId(), member.getPassword(), member.getCreatedDate(), member.getModifiedDate(), member.getRemainingVacationDays());
    }

}
