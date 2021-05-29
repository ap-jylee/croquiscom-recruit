package com.croquiscom.recruit.member.domain;

import com.croquiscom.recruit.common.BaseEntity;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "member")
@Getter
public class Member extends BaseEntity {

    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "password")
    private String password;

    @OneToOne
    @JoinColumn(name = "id")
    private MemberSetting memberSetting;

}
