package com.example.DoitU.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    @NotEmpty(message = "회원 아이디는 필수입니다.")
    private String userId;

    @NotEmpty(message = "회원 비밀번호는 필수입니다.")
    private String password;
}