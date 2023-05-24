package com.bside.BSIDE.user.persistence;

import org.apache.ibatis.annotations.Mapper;

import com.bside.BSIDE.user.domain.UserDto;


@Mapper
public interface UserMapper {
    void insertUser(UserDto userDto);
    int deleteUser(String email);
    void updateUser(UserDto userDto);

    UserDto getUserByEmail(String email);
    UserDto getUserByEmailPw(String email, String password);
    void saveTemporaryPassword(String email, String password);
}
