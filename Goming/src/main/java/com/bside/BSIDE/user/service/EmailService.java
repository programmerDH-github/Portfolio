package com.bside.BSIDE.user.service;

import org.springframework.web.multipart.MultipartFile;

public interface EmailService {
	String sendCodeMessage(String to) throws Exception;
	String sendTemporaryPassword(String to, String temporaryPassword) throws Exception;
	void sendByMonth(String email, String sendEmail, String date) throws Exception;
	void sendByMonthBlob(String email, String sendEmail, String date, MultipartFile imageData0,MultipartFile imageData1,MultipartFile imageData2) throws Exception;
	void scheduleMonthlyEmail() throws Exception;
}
