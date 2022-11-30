package com.p6.paymybuddy.controller;


import com.p6.paymybuddy.controller.dto.login.LoginRequest;
import com.p6.paymybuddy.service.data.Login;
import com.p6.paymybuddy.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class LoginController {
    private final LoginService loginService;

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }


    @GetMapping("logins")
    public ResponseEntity<List<Login>> getLogins() {
        try {
            return ResponseEntity.ok(loginService.getLogins());
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("login/{id}")
    public ResponseEntity<Login> getLogin(@PathVariable("id") final Long id) {
        try {
            return ResponseEntity.ok(loginService.getLogin(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("login")
    public ResponseEntity<Login> createLogin(@RequestBody LoginRequest login) {
        return ResponseEntity.ok(loginService.addLogin(login));
    }

    @PutMapping("/login/{id}")
    public ResponseEntity<Login> updateLogin(@PathVariable("id") final Long id, @RequestBody LoginRequest login) {
        try {
            return ResponseEntity.ok(loginService.updateLogin(id, login));
        } catch (NoSuchElementException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/login/{id}")
    public ResponseEntity<?> deleteLogin(@PathVariable("id") final Long id) {
        try {
            loginService.getLogin(id);
            loginService.deleteLogin(id);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException exception) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/logins")
    public ResponseEntity<?> deleteLogins() {
        loginService.deleteLogins();
        return ResponseEntity.noContent().build();
    }


    @GetMapping("login")
    public ResponseEntity<Login> searchEmailAndPassword(@RequestParam("email") final String email, @RequestParam("password") final String password) {
        try {
            return ResponseEntity.ok(loginService.searchEmailAndPassword(email, password));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("loginSearch")
    public ResponseEntity<Login> searchEmail(@RequestParam("emailLogin") final String emailLogin,@RequestParam("crediteur") final Long crediteur) {
        try {
            return ResponseEntity.ok(loginService.searchEmail(emailLogin,crediteur));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping("greeting")
//    @RolesAllowed("USER")
    public ResponseEntity<Login> login(@RequestParam("name") final String name) {
        try {
            log.info("OK");
            return ResponseEntity.ok(loginService.getAuthLogin(name));
        } catch (NoSuchElementException e){
            log.info("error");
            return ResponseEntity.notFound().build();
        }
    }

}
