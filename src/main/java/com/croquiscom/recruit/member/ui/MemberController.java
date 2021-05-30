package com.croquiscom.recruit.member.ui;

import com.croquiscom.recruit.member.application.MemberService;
import com.croquiscom.recruit.member.dto.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("identity")
    public ResponseEntity<MemberResponse> getIdentity(
            Authentication authentication) {
        return ResponseEntity.ok().body(memberService.getMember(authentication.getName()));
    }

}
