package com.croquiscom.recruit.member.application;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("member service test")
@SpringBootTest
public class MemberServiceTest {

    public static final String PASSWORD = "password";

    @Autowired
    private MemberService memberService;

    @DisplayName("load exist user")
    @ParameterizedTest
    @ValueSource(strings = { "user" })
    public void loadUserByUsername(String username) {
        User expected = new User(username, PASSWORD, Lists.newArrayList());
        UserDetails actual = memberService.loadUserByUsername(username);
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("load no exist user - username not found exception")
    @Test
    public void loadUserByUsernameThrowUsernameNotFoundException() {
        assertThatThrownBy(() -> memberService.loadUserByUsername("user1"))
                .isInstanceOf(UsernameNotFoundException.class);
    }

}
