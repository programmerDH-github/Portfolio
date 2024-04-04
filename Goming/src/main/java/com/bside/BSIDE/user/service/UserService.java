package com.bside.BSIDE.user.service;

import java.util.List;

import com.bside.BSIDE.user.domain.UserDto;

public interface UserService {	
	int deleteUser(String email);
	void updateUser(UserDto userDto);

	UserDto getUserByEmail(String email);

	UserDto getUserByEmailPw(String email, String password);
	void saveTemporaryPassword(String email, String password);
	List<UserDto> getUser();

	String getUserByUsrNm(String usrNm);

	boolean getPasswordConfirm(String email, String password);
}
