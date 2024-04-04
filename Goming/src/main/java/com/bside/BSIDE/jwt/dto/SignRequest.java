package com.bside.BSIDE.jwt.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @SignRequest
 * @작성자 DongHun
 * @일자 2024.01.11.
 **/

@Getter 
@Setter
public class SignRequest {

    private Long id;

    private String account;

    private String password;

    private String nickname;

    private String name;

    private String email;

}
