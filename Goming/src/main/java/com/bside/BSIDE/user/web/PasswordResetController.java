package com.bside.BSIDE.user.web;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;


import com.bside.BSIDE.user.domain.EmailDto;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bside.BSIDE.user.domain.UserDto;
import com.bside.BSIDE.user.service.EmailService;
import com.bside.BSIDE.user.service.UserService;

import io.swagger.v3.oas.annotations.Operation;

/**
 * @PasswordResetController
 * @작성자 DongHun
 * @일자 2023.05.12.
 **/

@CrossOrigin(origins = {"http://localhost:3000","http://www.goming.site"},allowCredentials = "true")
@RestController
@RequestMapping("/password")
public class PasswordResetController {

	private final UserService userService;
	private final EmailService emailService;

	public PasswordResetController(UserService userService, EmailService emailService) {
		this.userService = userService;
		this.emailService = emailService;
	}

	@PostMapping("/password-reset")
	@Operation(summary = "임시 비밀번호 발급")
	public String resetPassword(@RequestBody EmailDto param) {
		System.out.println("@#테스트");
		System.out.println(param.getEmail());
		// 1. 이메일 주소의 유효성 검사
		if (!isValidEmail(param.getEmail())) {
			return "유효하지 않은 이메일 주소입니다.";
		}

		// 2. 이메일 주소의 존재 여부 확인
		UserDto user = userService.getUserByEmail(param.getEmail());
		if (user == null) {
			return "해당 이메일로 가입된 사용자가 없습니다.";
		}

		// 3. 임시 비밀번호 생성 및 이메일 전송
		String temporaryPassword = generateTemporaryPassword();
		try {
			emailService.sendTemporaryPassword(param.getEmail(),temporaryPassword);
		} catch (Exception e) {
			return "이메일 전송에 실패했습니다.";
		}

		// 4. 임시 비밀번호 저장
		userService.saveTemporaryPassword(param.getEmail(), temporaryPassword);

		return "임시 비밀번호가 이메일로 전송되었습니다.";
	}

	/* 이메일 주소의 유효성 검사 */
	private boolean isValidEmail(String email) {
		String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
		return email.matches(regex);
	}

	/* 임시 비밀번호 생성 */
	public String generateTemporaryPassword() {
		int length = 8; // 생성할 임시 비밀번호의 길이
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"; // 사용할 문자열 범위
		StringBuilder sb = new StringBuilder();

		Random random = new Random();
		for (int i = 0; i < length; i++) {
			int index = random.nextInt(characters.length());
			sb.append(characters.charAt(index));
		}

		return sb.toString();
	}
	
	/* SHA-256 암호화 */
	private static String encryptPassword(String password) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));

			StringBuilder hexString = new StringBuilder();
			for (byte b : encodedHash) {
				String hex = Integer.toHexString(0xff & b);
				if (hex.length() == 1) {
					hexString.append('0');
				}
				hexString.append(hex);
			}

			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			// 암호화 알고리즘을 찾을 수 없는 경우 처리
			e.printStackTrace();
			throw new IllegalArgumentException("암호화 알고리즘을 찾을 수 없습니다.");
		}
	}
}
