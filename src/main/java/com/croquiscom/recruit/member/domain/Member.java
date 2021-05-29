package com.croquiscom.recruit.member.domain;

import com.croquiscom.recruit.common.BaseEntity;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "member")
@Getter
public class Member extends BaseEntity {

    @Id
    private String id;
    private String password;

}
