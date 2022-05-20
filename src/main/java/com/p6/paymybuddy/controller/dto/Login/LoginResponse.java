package com.p6.paymybuddy.controller.dto.Login;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginResponse {
    private Long idLogin;
    private String email;
    private String password;
}
