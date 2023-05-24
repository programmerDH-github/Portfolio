package com.bside.BSIDE.user.service;

import com.bside.BSIDE.user.domain.UserDto;
import com.bside.BSIDE.user.persistence.SignUpMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service("SignUpServiceImpl")
public class SignUpServiceImpl implements SignUpService{
    private final SignUpMapper signUpMapper;
    private JavaMailSender javaMailSender;

    @Override
    public void signUser(UserDto userDto) throws Exception {
         /*
        Todo: 추후 sns 로그인 추가 필요
         */
        // password 암호화
//        String password = passwordEncoder.encode(userDto.getPassword());
        // 회원가입
        signUpMapper.signUser(userDto);
    }

}
