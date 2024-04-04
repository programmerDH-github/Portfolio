//package com.bside.BSIDE.jwt;
//
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.bside.BSIDE.jwt.domain.MemberRepository;
//import com.bside.BSIDE.jwt.dto.Member;
//
//import lombok.RequiredArgsConstructor;
//
//@Service
//@RequiredArgsConstructor
//public class JpaUserDetailsService implements UserDetailsService {
//
//    private final MemberRepository memberRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        Member member = memberRepository.findByAccount(username).orElseThrow(
//                () -> new UsernameNotFoundException("Invalid authentication!")
//        );
//
//        return new CustomUserDetails(member);
//    }
//}
