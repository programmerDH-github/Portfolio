package com.bside.BSIDE.user.persistence;

import com.bside.BSIDE.user.domain.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface SignUpMapper {
    int duplicateCheck(String eml);
    void signUser(UserDto userDto);
    Map<String, Object> selectMember(String eml);
}
