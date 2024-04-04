package com.bside.BSIDE.user.service;


import com.bside.BSIDE.user.domain.UserDto;

import java.util.Map;

public interface SignUpService {
    // 회원가입
    void signUser(UserDto userDto) throws Exception;
}
