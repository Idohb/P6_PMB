package com.p6.paymybuddy.Mapper;


import com.p6.paymybuddy.Model.Entity.LoginEntity;
import com.p6.paymybuddy.Service.Data.Login;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LoginConverter {
    public Login mapperLogin(LoginEntity loginEntity) {
        Login login = new Login();
        login.setIdLogin(loginEntity.getIdLogin());
        login.setEmail(loginEntity.getEmail());
        login.setPassword(loginEntity.getPassword());

        return login;
    }

    public List<Login> mapperLogin(List<LoginEntity> loginEntities) {
        return loginEntities.stream().map(this::mapperLogin).collect(Collectors.toList());
    }
}
