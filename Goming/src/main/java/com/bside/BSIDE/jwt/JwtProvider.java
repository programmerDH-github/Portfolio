package com.bside.BSIDE.jwt;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.bside.BSIDE.jwt.dto.RedisDao;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @JwtProvider
 * @작성자 DongHun
 * @일자 2024.01.11.
 **/

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtProvider {

//    private final byte[] accessSecret;
//    private final byte[] refreshSecret;
	private final RedisDao redisDao;
	private static final String AUTHORITIES_KEY = "auth";
    private static final String BEARER_TYPE = "Bearer";
    
    @Value("${jwt.secretkey}")
    private String secretKey;
    private Key accessSecret;
    
    @Value("${jwt.refreshKey}")
    private String refreshKey;
    private Key refreshSecret;
    
    @PostConstruct
    protected void init() {
    	accessSecret = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        refreshSecret = Keys.hmacShaKeyFor(refreshKey.getBytes(StandardCharsets.UTF_8));
    }

    public final static Long ACCESS_TOKEN_EXPIRE_COUNT = 30 * 60 * 1000L; // 30 minutes
    public final static Long REFRESH_TOKEN_EXPIRE_COUNT = 7 * 24 * 60 * 60 * 1000L; // 7 days

    /**
     * AccessToken 생성
     */
    public String createAccessToken(Long id, String email, List<String> roles) {
        return createToken(id, email, roles, ACCESS_TOKEN_EXPIRE_COUNT, accessSecret);
    }

    /**
     * RefreshToken 생성
     */
    public String createRefreshToken(Long id, String email, List<String> roles) {
        return createToken(id, email, roles, REFRESH_TOKEN_EXPIRE_COUNT, refreshSecret);
    }


    private String createToken(Long id, String email, List<String> roles,
                               Long expire, Key secretKey) {
        Claims claims = Jwts.claims().setSubject(email);

        claims.put("roles", roles);
        claims.put("userId", id);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expire))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * 토큰에서 유저 아이디 얻기
     */
    public Long getUserIdFromToken(String token) {
        String[] tokenArr = token.split(" ");
        token = tokenArr[1];
        Claims claims = parseToken(token, accessSecret);
        return Long.valueOf((Integer)claims.get("userId"));
    }

    public Claims parseAccessToken(String accessToken) {
        return parseToken(accessToken, accessSecret);
    }

    public Claims parseRefreshToken(String refreshToken) {
        return parseToken(refreshToken, refreshSecret);
    }


    public Claims parseToken(String token, Key secretKey) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    /**
    * JWT 토큰을 복호화하여 토큰에 들어있는 정보를 꺼내는 메서드
    */
    public Authentication getAuthentication(String accessToken) {
        // 토큰 복호화
        Claims claims = parseAccessToken(accessToken);


        // 클레임에서 권한 정보 가져오기
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        // UserDetails 객체를 만들어서 Authentication 리턴
        UserDetails principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }
    
//    // Authorization Header를 통해 인증을 한다.
//    public String resolveToken(HttpServletRequest request) {
//        return request.getHeader("Authorization");
//    }
    
    public Long getExpiration(String accessToken) {
        // accessToken 남은 유효시간
        Date expiration = Jwts.parserBuilder()
                .setSigningKey(accessSecret)
                .build()
                .parseClaimsJws(accessToken)
                .getBody()
                .getExpiration();
        // 현재 시간
        Long now = new Date().getTime();
        return (expiration.getTime() - now);
    }

    /**
     * @param secretKey - byte형식
     * @return Key 형식 시크릿 키
     */
    public static Key getSigningKey(byte[] secretKey) {
        return Keys.hmacShaKeyFor(secretKey);
    }
}