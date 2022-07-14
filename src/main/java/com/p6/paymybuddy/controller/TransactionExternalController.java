package com.p6.paymybuddy.controller;

import com.p6.paymybuddy.controller.dto.transactionExternal.TransactionExternalRequest;
import com.p6.paymybuddy.service.TransactionExternalService;
import com.p6.paymybuddy.service.data.TransactionExternal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class TransactionExternalController {
    private final TransactionExternalService transactionExternalService;

    @Autowired
    public TransactionExternalController(TransactionExternalService transactionExternalService) {
        this.transactionExternalService = transactionExternalService;
    }

    @GetMapping("transactionExternals")
    public ResponseEntity<List<TransactionExternal>> getTransactionExternals() {
        try {
            return ResponseEntity.ok(transactionExternalService.getTransactionExternals());
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("transactionExternal/{id}")
    public ResponseEntity<TransactionExternal> getTransactionExternal(@PathVariable("id") final Long id) {
        try {
            return ResponseEntity.ok(transactionExternalService.getTransactionExternal(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
    //findByUser
    @GetMapping("transactionExternalByUser/{id}")
    public ResponseEntity<List<TransactionExternal>> getTransactionExternalByUser(@PathVariable("id") final Long id) {
        try {
            log.info("ok");
            return ResponseEntity.ok(transactionExternalService.getTransactionExternalByUser(id));
        } catch (NoSuchElementException e) {
            log.info("error");
            return ResponseEntity.notFound().build();
        }
    }

        @PostMapping("transactionExternal")
        public ResponseEntity<TransactionExternal> createTransactionExternal(@RequestBody TransactionExternalRequest transactionExternal) {
            try {
                log.info("create Transaction in progress");
                return ResponseEntity.ok(transactionExternalService.addTransactionExternal(transactionExternal));
            }
            catch (NoSuchElementException e) {
                log.info("Error");
                return ResponseEntity.notFound().build();
            }
        }
        @PutMapping("transactionExternal/{id}")
        public ResponseEntity<TransactionExternal> updateTransactionExternal(@PathVariable("id") final Long id, @RequestBody TransactionExternalRequest transactionExternalRequest) {
            try {
                return ResponseEntity.ok(transactionExternalService.updateTransactionExternal(id, transactionExternalRequest));
            } catch (NoSuchElementException exception) {
                return ResponseEntity.notFound().build();
            }
        }

}
