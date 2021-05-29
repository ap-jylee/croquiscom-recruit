package com.croquiscom.recruit.member.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("member repository test")
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @DisplayName("equal member")
    @Test
    public void isEqualMember() {
        Member member = memberRepository.findById("user").orElseThrow();
        MemberMock mock = MemberMock.of(member);
        assertThat(mock.isEqualMember("user")).isTrue();
    }

    @DisplayName("full remaining vacation days")
    @Test
    public void isFullRemainingVacationDays() {
        Member member = memberRepository.findById("user").orElseThrow();
        MemberMock mock = MemberMock.of(member);
        assertThat(mock.isFullRemainingVacationDays()).isTrue();
    }

}
