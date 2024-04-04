//package com.bside.BSIDE.jwt.controller;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.bside.BSIDE.jwt.JwtProvider;
//
//import jakarta.servlet.http.HttpServletRequest;
//import lombok.RequiredArgsConstructor;
//import lombok.experimental.Helper;
//
//@CrossOrigin
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/auth")
//public class AuthController {
//	
//	private final JwtProvider jwtProvider;
//	
//	@GetMapping("/reissue")
//	public ResponseEntity<?> reissue(HttpServletRequest request) {
//	    //1. Request Header 에서 JWT Token 추출
//	    String token = jwtProvider.resolveToken(request);
//
//	    //2. validateToken 메서드로 토큰 유효성 검사
//	    if (token != null && jwtProvider.validateToken(token)) {
//
//	        //3. 저장된 refresh token 찾기 
//	        RefreshToken refreshToken = refreshTokenRedisRepository.findByRefreshToken(token);
//	        if (refreshToken != null) {
//	            //4. 최초 로그인한 ip 와 같은지 확인 (처리 방식에 따라 재발급을 하지 않거나 메일 등의 알림을 주는 방법이 있음)
//	            String currentIpAddress = Helper.getClientIp(request);
//	            if (refreshToken.getIp().equals(currentIpAddress)) {
//	                // 5. Redis 에 저장된 RefreshToken 정보를 기반으로 JWT Token 생성
//	                UserResponseDto.TokenInfo tokenInfo = jwtTokenProvider.generateToken(refreshToken.getId(), refreshToken.getAuthorities());
//
//	                // 6. Redis RefreshToken update
//	                refreshTokenRedisRepository.save(RefreshToken.builder()
//	                        .id(refreshToken.getId())
//	                        .ip(currentIpAddress)
//	                        .authorities(refreshToken.getAuthorities())
//	                        .refreshToken(tokenInfo.getRefreshToken())
//	                        .build());
//	                return response.success(tokenInfo);
//	            }
//	        }
//	    }
//	    return response.fail("토큰 갱신에 실패했습니다.");
//	}
//}
