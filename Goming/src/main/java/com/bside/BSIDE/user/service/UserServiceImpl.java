package com.bside.BSIDE.user.service;

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

}
