package com.p6.paymybuddy.Controller.Dto.Login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginRequest {
    private String email;
    private String password;

}
