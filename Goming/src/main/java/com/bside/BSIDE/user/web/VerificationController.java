package com.bside.BSIDE.user.web;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * @VerificationController
 * @작성자 DongHun
 * @일자 2023.05.12.
 **/

@CrossOrigin(origins = {"http://localhost:3000","http://www.goming.site"},allowCredentials = "true")
@RestController
@RequestMapping("/verifyCode")
public class VerificationController {
	
	/* 인증 번호 검증 */
	@PostMapping("/verify")
	@Operation(summary = "발급된 인증 번호 확인", description = "String userCode")
	public String verifyCode(@RequestParam("code") String userCode,  HttpServletRequest request) {
		HttpSession session = request.getSession();
		String verificationCode = (String) session.getAttribute("verificationCode");

		System.out.println(verificationCode +"$!$!@$!@@!@$@$");
		if (verificationCode != null && verificationCode.equals(userCode)) {
            // 인증번호 일치
            return "인증번호가 일치합니다.";
        } 
		else 
		{
            // 인증번호 불일치
            return "인증번호가 일치하지 않습니다.";
        }
	}
}
