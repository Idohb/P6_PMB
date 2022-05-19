package com.p6.paymybuddy.Service.Data;

import com.p6.paymybuddy.Model.Entity.LoginEntity;
import lombok.Data;

@Data
public class Login {
    private Long idLogin;
    private String email;
    private String password;

    public Login() {
    }

    public Login(LoginEntity p) {
        setIdLogin(p.getIdLogin());
        setEmail(p.getEmail());
        setPassword(p.getPassword());
    }

}
