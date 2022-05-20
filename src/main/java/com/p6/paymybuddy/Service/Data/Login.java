package com.p6.paymybuddy.Service.Data;

import com.p6.paymybuddy.Model.Entity.LoginEntity;
import lombok.Data;

@Data
public class Login {
    private Long id;
    private String email;
    private String password;

    public Login() {
    }

}
