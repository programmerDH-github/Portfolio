package com.bside.BSIDE.user.persistence;

import java.util.List;

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
    List<UserDto> getUser();    String getUserByUsrNm(String usrNm);
    String getPasswordConfirm(String email, String password);
}
