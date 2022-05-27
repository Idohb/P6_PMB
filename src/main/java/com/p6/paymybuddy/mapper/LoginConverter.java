package com.p6.paymybuddy.mapper;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.p6.paymybuddy.model.entity.LoginEntity;
import com.p6.paymybuddy.service.data.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LoginConverter {

    @Autowired
    private ObjectMapper objectMapper;
    public Login mapperLogin(LoginEntity loginEntity) {
        return objectMapper.convertValue(loginEntity, Login.class);
    }

    public List<Login> mapperLogin(List<LoginEntity> loginEntities) {
        return loginEntities.stream().map(this::mapperLogin).collect(Collectors.toList());
    }
}
