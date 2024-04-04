package com.bside.BSIDE.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bside.BSIDE.user.domain.UserDto;
import com.bside.BSIDE.user.persistence.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	private final UserMapper userMapper;

	public UserServiceImpl(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	@Override
	public int deleteUser(String email) {
		return userMapper.deleteUser(email);
	}

	@Override
	public void updateUser(UserDto userDto) {
		userMapper.updateUser(userDto);
	}

	@Override
	public UserDto getUserByEmail(String email) {
		return userMapper.getUserByEmail(email);
	}

	@Override
	public UserDto getUserByEmailPw(String email, String password) {
		return userMapper.getUserByEmailPw(email, password);
	}

	@Override
	public void saveTemporaryPassword(String email, String password) {
		userMapper.saveTemporaryPassword(email, password);
	}
	
	@Override
	public List<UserDto> getUser() {
		return userMapper.getUser();
	}

	@Override
	public String getUserByUsrNm(String usrNm) {
		return userMapper.getUserByUsrNm(usrNm);
	}

	@Override
	public boolean getPasswordConfirm(String email, String password) {
		return userMapper.getPasswordConfirm(email,password).equals(password);
	}

}
