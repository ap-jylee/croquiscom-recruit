package com.croquiscom.recruit.member.application;

import com.croquiscom.recruit.member.domain.Member;
import com.croquiscom.recruit.member.domain.MemberRepository;
import com.croquiscom.recruit.member.dto.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("username not found exception."));
        List<GrantedAuthority> authorities = new ArrayList<>();
        GrantedAuthority role = new SimpleGrantedAuthority("ROLE_USER");
        authorities.add(role);
        return new User(member.getId(), member.getPassword(), authorities);
    }

    public MemberResponse getMember(String memberId) {
        Member persistMember = memberRepository.findById(memberId).orElseThrow();
        return MemberResponse.of(persistMember);
    }

}
