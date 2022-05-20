package com.p6.paymybuddy.controller;

import com.p6.paymybuddy.controller.dto.Commission.CommissionRequest;
import com.p6.paymybuddy.Service.CommissionService;
import com.p6.paymybuddy.Service.Data.Commission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class CommissionController {

    private final CommissionService commissionService;

    @Autowired
    public CommissionController(CommissionService commissionService) {this.commissionService = commissionService;}

    @GetMapping("commissions")
    public ResponseEntity<List<Commission>> getCommissions() {
        try {
            return ResponseEntity.ok(commissionService.getCommissions());
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("commission/{id}")
    public ResponseEntity<Commission> getCommission(@PathVariable("id") final Long id) {
        try {
            return ResponseEntity.ok(commissionService.getCommission(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("commission")
    public ResponseEntity<Commission> createCommission(@RequestBody CommissionRequest commission) {
        try {
            return ResponseEntity.ok(commissionService.addCommission(commission.getTransactionId()));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/commission/{id}")
    public ResponseEntity<Commission> updateCommission(@PathVariable("id") final Long id, @RequestBody CommissionRequest commission) {
        try {
            return ResponseEntity.ok(commissionService.updateCommission(id, commission));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("commissionByTransaction")
    public ResponseEntity<Commission> searchCommissionByTransaction(@RequestParam("transaction_id") final Long transaction_id) {
        try {
            return ResponseEntity.ok(commissionService.searchCommissionByTransaction(transaction_id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
