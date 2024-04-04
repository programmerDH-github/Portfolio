package com.bside.BSIDE.user.web;

import com.bside.BSIDE.user.domain.EmailDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.Cookie;
import com.bside.BSIDE.user.service.EmailService;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.multipart.MultipartFile;
import java.net.http.HttpResponse;

/**
 * @EmailController
 * @작성자 DongHun
 * @일자 2023.05.10.
 **/


@CrossOrigin(origins = {"http://localhost:3000","http://www.goming.site"},allowCredentials = "true")
@RestController
@RequestMapping("/email")public class EmailController {


	private final EmailService emailService;
	
	public EmailController(EmailService emailService) {
		this.emailService = emailService;
	}
	
	/* 이메일 인증 번호 전송 */
	@PostMapping("/emailConfirm")
	@Operation(summary = "이메일 인증 번호 전송")
	public String emailConfirm(@RequestBody EmailDto param,HttpServletRequest request, HttpServletResponse response) throws Exception {



//		Cookie[] cookies = request.getCookies();
//		if (cookies != null) {
//			for (Cookie cookie : cookies) {
//				if ("JSESSIONID".equals(cookie.getName())) {
//					cookie.setSecure(true); // HTTPS를 사용하는 경우에만
//					cookie.setHttpOnly(true);
//					cookie.setSameSite("None"); // 또는 "Lax" 또는 "Strict" 등을 사용
//					httpResponse.addCookie(cookie);
//				}
//			}
//		}
		// 응답 헤더에 쿠키 추가



		return emailService.sendCodeMessage(param.getEmail());
	}
	
	/* 월간고밍 페이지에서 ‘이메일로 보내기’ 버튼을 눌렀을 때 */
    @PostMapping("/sendByMonth")
    @Operation(summary = "월간 고밍 이메일로 전송")
    public void sendByMonth(@RequestBody EmailDto param) throws Exception {
        System.out.println(param.getEmail()+"+ @#@#@##@#@#@#!@$@$!@$email");
        System.out.println(param.getSendEmail()+"+ @#@#@##@#@#@#!@$@$!@sendEmail");
        System.out.println(param.getDate()+ "+ @#@#@##@#@#@#!@$@$!@date");

        emailService.sendByMonth(param.getEmail(),param.getSendEmail(),param.getDate());
    }
    @PostMapping("/sendByMonthBlob")
    @Operation(summary = "월간 고밍이미지 이메일로 전송")
    public void sendByMonth(@RequestParam String email, @RequestParam String sendEmail, @RequestParam String date ,@RequestParam(required=false) MultipartFile imageData0,@RequestParam(required=false) MultipartFile imageData1 ,@RequestParam(required=false) MultipartFile imageData2 ) throws Exception {

        emailService.sendByMonthBlob(email,sendEmail,date,imageData0,imageData1,imageData2);
    }

//	@PostMapping("/api/upload-image")
//	@Operation(summary = "월간 고밍 이메일로 전송(테스트)")
//	public void sendByMonthPdf(@RequestParam String email, @RequestParam String sendEmail, @RequestParam MultipartFile formData) throws Exception {
//		System.out.println(email+"+ @#@#@##@#@#@#!@$@$!@$email");
//		System.out.println(sendEmail+"+ @#@#@##@#@#@#!@$@$!@sendEmail");
//		System.out.println(formData+ "+ @#@#@##@#@#@#!@$@$!@date");
//
////		emailService.sendByMonth(param.getEmail(),param.getSendEmail(),param.getDate());
//	}
	/* 월간 고밍 & 리마인드 메일 */
	@Scheduled(cron = "0 0 0 1 * *")	//매월 1일 전송
	@GetMapping("/scheduleMonthlyEmail")
	@Operation(summary = "매일 1일 월간 고밍 자동 전송")
	public void scheduleMonthlyEmail() throws Exception {
		emailService.scheduleMonthlyEmail();
	}
		
}
