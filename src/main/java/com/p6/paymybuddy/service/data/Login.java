package com.p6.paymybuddy.service.data;

import lombok.Data;

@Data
public class Login {
    private Long id;
    private String email;
    private String password;

    public Login() {
    }



}
