package com.p6.paymybuddy.service;

import com.p6.paymybuddy.controller.dto.transactionExternal.TransactionExternalRequest;
import com.p6.paymybuddy.mapper.TransactionExternalConverter;
import com.p6.paymybuddy.model.entity.PersonEntity;
import com.p6.paymybuddy.model.entity.TransactionExternalEntity;
import com.p6.paymybuddy.model.repository.BankRepository;
import com.p6.paymybuddy.model.repository.PersonRepository;
import com.p6.paymybuddy.model.repository.TransactionExternalRepository;
import com.p6.paymybuddy.service.data.TransactionExternal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TransactionExternalService {

    @Autowired
    private TransactionExternalConverter transactionExternalConverter;

    @Autowired
    private TransactionExternalRepository transactionExternalRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private BankRepository bankRepository;

    public List<TransactionExternal> getTransactionExternals() {
        return transactionExternalConverter.mapperTransactionExternal( transactionExternalRepository.findAll());
    }

    public TransactionExternal getTransactionExternal(final Long id) {
        TransactionExternalEntity transactionExternalEntity = transactionExternalRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Person id " + id + " not found"));
        return transactionExternalConverter.mapperTransactionExternal(transactionExternalEntity);
    }

    public TransactionExternal addTransactionExternal(TransactionExternalRequest transactionExternalRequest) {
        PersonEntity peUser = personRepository.findById(transactionExternalRequest.getUser()).orElseThrow(() -> new NoSuchElementException("Id Crediteur : " + transactionExternalRequest.getUser() + " not found"));

        TransactionExternalEntity transactionExternalEntity = new TransactionExternalEntity();
        return transactionExternalConverter.mapperTransactionExternal(transactionExternalEntity);
    }


}
