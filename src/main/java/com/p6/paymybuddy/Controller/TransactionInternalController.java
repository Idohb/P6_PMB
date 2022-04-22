package com.p6.paymybuddy.Controller;


import com.p6.paymybuddy.Controller.Dto.TransactionInternal.TransactionInternalRequest;
import com.p6.paymybuddy.Model.Entity.PersonEntity;
import com.p6.paymybuddy.Service.Data.Login;
import com.p6.paymybuddy.Service.Data.Person;
import com.p6.paymybuddy.Service.Data.TransactionInternal;
import com.p6.paymybuddy.Service.TransactionInternalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class TransactionInternalController {
    private final TransactionInternalService transactionInternalService;

    @Autowired
    public TransactionInternalController(TransactionInternalService transactionInternalService) {
        this.transactionInternalService = transactionInternalService;
    }


    @GetMapping("transactionInternals")
    public ResponseEntity<List<TransactionInternal>> getTransactionInternals() {
        try {
            return ResponseEntity.ok(transactionInternalService.getTransactionInternals());
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("transactionInternal/{id}")
    public ResponseEntity<TransactionInternal> getTransactionInternal(@PathVariable("id") final Long id) {
        try {
            return ResponseEntity.ok(transactionInternalService.getTransactionInternal(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("transactionInternalByCrediteur/{id}")
    public ResponseEntity<List<TransactionInternal>> getTransactionInternalByCrediteur(@PathVariable("id") final Long id) {
        try {
            log.info("ok");
            return ResponseEntity.ok(transactionInternalService.getTransactionInternalByCrediteur(id));
        } catch (NoSuchElementException e) {
            log.info("error");
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("transactionInternal")
    public ResponseEntity<TransactionInternal> createTransactionInternal(@RequestBody TransactionInternalRequest transactionInternal) {
        return ResponseEntity.ok(transactionInternalService.addTransactionInternal(transactionInternal));
    }

    @PutMapping("/transactionInternal/{id}")
    public ResponseEntity<TransactionInternal> updateTransactionInternal(@PathVariable("id") final Long id, @RequestBody TransactionInternalRequest transactionInternal) {
        try {
            return ResponseEntity.ok(transactionInternalService.updateTransactionInternal(id, transactionInternal));
        } catch (NoSuchElementException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/transactionInternal/{id}")
    public ResponseEntity<?> deleteTransactionInternal(@PathVariable("id") final Long id) {
        try {
            transactionInternalService.getTransactionInternal(id);
            transactionInternalService.deleteTransactionInternal(id);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException exception) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/transactionInternals")
    public ResponseEntity<?> deleteTransactionInternals() {
        transactionInternalService.deleteTransactionInternals();
        return ResponseEntity.noContent().build();
    }

}


//package com.p6.paymybuddy.Controller;
//
//public class TransactionInternalController {
//}
