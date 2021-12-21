package com.p6.paymybuddy.Controller;


import com.p6.paymybuddy.Controller.Dto.Login.LoginRequest;
import com.p6.paymybuddy.Service.Data.Login;
import com.p6.paymybuddy.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {
    private final LoginService loginService;

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
    public ResponseEntity<List<Login>> searchEmailAndPassword(@RequestParam("email") final String email, @RequestParam("password") final String password) {
        try {
            List<Login> le = loginService.searchEmailAndPassword(email, password);
            System.out.println(le.get(0).getEmail());
            System.out.println(le.get(0).getPassword());
            return ResponseEntity.ok(le);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
