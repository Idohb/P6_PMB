package com.p6.paymybuddy.Service;

import com.p6.paymybuddy.Controller.Dto.Login.LoginRequest;
import com.p6.paymybuddy.Mapper.LoginConverter;
import com.p6.paymybuddy.Model.Entity.LoginEntity;
import com.p6.paymybuddy.Model.Repository.LoginRepository;
import com.p6.paymybuddy.Service.Data.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LoginService {


    // Add constructor
    @Autowired
    private LoginConverter loginConverter;
    @Autowired
    private LoginRepository loginRepository;

    public LoginService() {
    }

    public List<Login> getLogins() {
        return loginConverter.mapperLogin( loginRepository.findAll());
    }

    public Login getLogin(final Long id) {
        LoginEntity loginEntity = loginRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Id " + id + " not found"));
        return loginConverter.mapperLogin(loginEntity);
    }


    public Login addLogin(LoginRequest loginRequest) {
        LoginEntity loginEntity = new LoginEntity(0L,
                loginRequest.getEmail(),
                loginRequest.getPassword());
        loginEntity = loginRepository.save(loginEntity);
        return loginConverter.mapperLogin(loginEntity);

    }

    public Login updateLogin(final Long id, LoginRequest loginRequest) {

        LoginEntity entity = loginRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Id " + id + " not found"));
        updateEntity(entity, loginRequest);
        entity = loginRepository.save(entity);
        return loginConverter.mapperLogin(entity);

    }

    public void deleteLogin(final Long id) {
        loginRepository.deleteById(id);
    }

    public void deleteLogins() {
        loginRepository.deleteAll();
    }

    private void updateEntity(LoginEntity loginEntity, LoginRequest loginRequest) {

        if (loginRequest.getEmail() != null)
            loginEntity.setEmail(loginRequest.getEmail());

        if (loginRequest.getPassword() != null)
            loginEntity.setPassword(loginRequest.getPassword());

    }
}
