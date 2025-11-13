package com.wanted.growthmate.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserSignUpRequestDto {


    @Email
    @NotBlank(message = "아이디를 입력해주세요.")
    private String username;


    @NotBlank(message = "패스워드를 입력해주세요.")
    private String password;


    @NotBlank
    private String role;

}
