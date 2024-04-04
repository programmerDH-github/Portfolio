package com.bside.BSIDE.user.web;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bside.BSIDE.user.domain.UserDto;
import com.bside.BSIDE.user.service.SignUpService;
import com.bside.BSIDE.user.service.UserService;

import io.swagger.v3.oas.annotations.Operation;

/**
 * @SignUpController
 * @작성자 DongHun
 * @일자 2023.05.10.
 **/

@CrossOrigin
@RestController
@RequestMapping("/user")
public class SignUpController {

    private final SignUpService signUpService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    
    public SignUpController(SignUpService signUpService, UserService userService, PasswordEncoder passwordEncoder) {
    	this.signUpService = signUpService;
    	this.userService = userService;
    	this.passwordEncoder = passwordEncoder;
    }
    

    @PostMapping("/register")
    @Operation(summary = "회원가입", description = "UserDto userDto")
    public ResponseEntity<?> register(@RequestBody UserDto userDto) throws Exception {
    	userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        signUpService.signUser(userDto);
        String result = "회원가입이 완료되었습니다.";
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
    
    @PostMapping("/check-email")
    @Operation(summary = "이메일 체크", description = "String eml")
    public ResponseEntity<String> checkEmailAvailability(@RequestParam("email") String email) {
    	String result;
    	HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        
    	UserDto user = userService.getUserByEmail(email);
    	
        if(!isValidEmail(email)) {
        	result = "유효하지 않은 이메일 주소입니다.";
        	return new ResponseEntity<>(result, headers, HttpStatus.CONFLICT);
        }
        
        if (user == null) {
        	result = "사용 가능한 이메일입니다.";
            return new ResponseEntity<>(result, headers, HttpStatus.OK);  // 이메일 사용 가능
        } else {
        	result = "이미 사용중인 이메일입니다.";
            return new ResponseEntity<>(result, headers, HttpStatus.CONFLICT);  // 이메일 중복
        }
    }
    
    /* 이메일 주소의 유효성 검사 */
	private boolean isValidEmail(String email) {
		String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
		return email.matches(regex);
	}
    @PostMapping("/check-usrnm")
    @Operation(summary = "닉네임 체크", description = "String usrNm")
    public ResponseEntity<String> checkNickNameAvailability(@RequestParam("nickName") String usrNm) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        String user = userService.getUserByUsrNm(usrNm);
        System.out.println("test " +user);
        if(user==null) {
            return new ResponseEntity<>("", headers, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(user, headers, HttpStatus.OK);
        }
    }
}
