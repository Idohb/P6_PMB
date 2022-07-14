package com.p6.paymybuddy.service;

import com.p6.paymybuddy.controller.dto.transactionExternal.TransactionExternalRequest;
import com.p6.paymybuddy.mapper.TransactionExternalConverter;
import com.p6.paymybuddy.model.entity.BankEntity;
import com.p6.paymybuddy.model.entity.PersonEntity;
import com.p6.paymybuddy.model.entity.TransactionExternalEntity;
import com.p6.paymybuddy.model.repository.BankRepository;
import com.p6.paymybuddy.model.repository.PersonRepository;
import com.p6.paymybuddy.model.repository.TransactionExternalRepository;
import com.p6.paymybuddy.service.data.TransactionExternal;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        PersonEntity peUser = personRepository.findById(transactionExternalRequest.getUser())
                .orElseThrow(() -> new NoSuchElementException("Id Crediteur : " + transactionExternalRequest.getUser() + " not found"));

        TransactionExternalEntity transactionExternalEntity = this.createTransactionExternal(transactionExternalRequest, peUser);

        return transactionExternalConverter.mapperTransactionExternal(transactionExternalEntity);
    }
    
        private TransactionExternalEntity createTransactionExternal (TransactionExternalRequest transactionExternalRequest, PersonEntity peUser) {
            LocalDateTime date = LocalDateTime.now();
            TransactionExternalEntity transactionExternalEntity = new TransactionExternalEntity(0L,
                    transactionExternalRequest.getDescription(),
                    transactionExternalRequest.getAmount(),
                    date.toString(),
                    peUser);


            transactionExternalEntity = transactionExternalRepository.save(transactionExternalEntity);
            return transactionExternalEntity;
        }


    public TransactionExternal updateTransactionExternal(final Long id, TransactionExternalRequest transactionExternalRequest) {
        TransactionExternalEntity entity = transactionExternalRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Id " + id + " not found"));
        updateEntity(entity, transactionExternalRequest);
        entity = transactionExternalRepository.save(entity);
        return transactionExternalConverter.mapperTransactionExternal(entity);
    }

    private void updateEntity(TransactionExternalEntity transactionExternalEntity, TransactionExternalRequest transactionExternalRequest) {

        if (transactionExternalRequest.getDescription() != null)
            transactionExternalEntity.setDescription(transactionExternalRequest.getDescription());

        if (transactionExternalRequest.getAmount() != null)
            transactionExternalEntity.setAmount(transactionExternalRequest.getAmount());

        if (transactionExternalRequest.getTimeTransaction() != null)
            transactionExternalEntity.setTimeTransaction(transactionExternalRequest.getTimeTransaction());

    }

    public void processTransactionExternal(Double amount, String description, PersonEntity pe) {
        LocalDateTime date = LocalDateTime.now();
        TransactionExternalEntity transactionExternalEntity = new TransactionExternalEntity(
                0L,
                description,
                amount,
                date.toString(),
                pe
        );
        transactionExternalRepository.save(transactionExternalEntity);
    }

    public List<TransactionExternal> getTransactionExternalByUser(final Long id) {
        PersonEntity crediteur = personRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Person id " + id + " not found"));
        List<TransactionExternalEntity> transactionExternalEntity = transactionExternalRepository.findByUser(crediteur)
                .orElseThrow(() -> new NoSuchElementException("Id " + id + " not found"));
        return transactionExternalConverter.mapperTransactionExternal(transactionExternalEntity);
    }
}
