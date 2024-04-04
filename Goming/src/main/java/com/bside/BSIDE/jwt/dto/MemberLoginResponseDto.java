package com.bside.BSIDE.jwt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberLoginResponseDto {
    private String accessToken;
    private String refreshToken;

    private Long memberId;
    private String name;
}
