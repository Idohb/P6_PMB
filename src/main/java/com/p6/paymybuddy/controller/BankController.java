package com.p6.paymybuddy.controller;

import com.p6.paymybuddy.service.BankService;
import com.p6.paymybuddy.service.data.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class BankController {

    private final BankService bankService;

    @Autowired
    public BankController(BankService bankService){this.bankService = bankService;}

    @GetMapping("banks")
    public ResponseEntity<List<Bank>> getBanks() {
        try {
            return ResponseEntity.ok(bankService.getBanks());
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
