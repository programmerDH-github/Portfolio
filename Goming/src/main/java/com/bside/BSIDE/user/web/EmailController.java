package com.bside.BSIDE.user.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bside.BSIDE.user.service.EmailService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * @EmailController
 * @작성자 DongHun
 * @일자 2023.05.10.
 **/

@RestController
@RequestMapping("/email")
public class EmailController {
	
	private final EmailService emailService;
	
	public EmailController(EmailService emailService) {
		this.emailService = emailService;
	}
	
	/* 이메일 인증 번호 전송 */
	@PostMapping("/emailConfirm")
	@Operation(summary = "이메일 인증 번호 전송")
	public String emailConfirm(@RequestParam String email) throws Exception {
	  return emailService.sendCodeMessage(email);
	}
		
}
