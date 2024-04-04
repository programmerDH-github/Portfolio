package com.bside.BSIDE.jwt.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bside.BSIDE.jwt.dto.Member;

import jakarta.transaction.Transactional;

@Transactional
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByAccount(String account);
}
