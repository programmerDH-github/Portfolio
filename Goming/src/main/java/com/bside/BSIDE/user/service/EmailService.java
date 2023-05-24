package com.bside.BSIDE.user.service;

public interface EmailService {
	 String sendCodeMessage(String to)throws Exception;
	 String sendTemporaryPassword(String to, String temporaryPassword)throws Exception;
}
