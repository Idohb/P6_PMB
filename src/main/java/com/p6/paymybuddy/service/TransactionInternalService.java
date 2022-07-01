package com.p6.paymybuddy.service;

import com.p6.paymybuddy.controller.dto.transactionInternal.TransactionInternalRequest;
import com.p6.paymybuddy.mapper.TransactionInternalConverter;
import com.p6.paymybuddy.model.entity.BankEntity;
import com.p6.paymybuddy.model.entity.PersonEntity;
import com.p6.paymybuddy.model.entity.TransactionInternalEntity;
import com.p6.paymybuddy.model.repository.BankRepository;
import com.p6.paymybuddy.model.repository.PersonRepository;
import com.p6.paymybuddy.model.repository.TransactionInternalRepository;
import com.p6.paymybuddy.service.data.Commission;
import com.p6.paymybuddy.service.data.TransactionInternal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import java.util.NoSuchElementException;

@Service
public class TransactionInternalService {


    // Add constructor
    @Autowired
    private TransactionInternalConverter transactionInternalConverter;
    @Autowired
    private TransactionInternalRepository transactionInternalRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private CommissionService commissionService;

    public TransactionInternalService() {
    }

    public List<TransactionInternal> getTransactionInternals() {
        return transactionInternalConverter.mapperTransactionInternal( transactionInternalRepository.findAll());
    }

    public TransactionInternal getTransactionInternal(final Long id) {
        TransactionInternalEntity transactionInternalEntity = transactionInternalRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Id " + id + " not found"));
        return transactionInternalConverter.mapperTransactionInternal(transactionInternalEntity);
    }

    public List<TransactionInternal> getTransactionInternalByCrediteur(final Long id) {
        PersonEntity crediteur = personRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Person id " + id + " not found"));
        List<TransactionInternalEntity> transactionInternalEntity = transactionInternalRepository.findByCrediteur(crediteur).orElseThrow(() -> new NoSuchElementException("Id " + id + " not found"));
        return transactionInternalConverter.mapperTransactionInternal(transactionInternalEntity);
    }
    @Transactional
    public TransactionInternal addTransactionInternal(TransactionInternalRequest transactionInternalRequest) {
        PersonEntity peCrediteur = personRepository.findById(transactionInternalRequest.getCrediteur()).orElseThrow(() -> new NoSuchElementException("Id Crediteur : " + transactionInternalRequest.getCrediteur() + " not found"));
        PersonEntity peDebiteur  = personRepository.findById(transactionInternalRequest.getDebiteur()).orElseThrow(()  -> new NoSuchElementException("Id Debiteur : "  + transactionInternalRequest.getDebiteur()  + " not found"));


        this.bankTransaction(peCrediteur,peDebiteur,transactionInternalRequest.getAmount());
        TransactionInternalEntity transactionInternalEntity = this.createTransactionInternal(transactionInternalRequest, peCrediteur, peDebiteur);
        this.commissionService.processCommission(transactionInternalEntity.getId(), peCrediteur);

        return transactionInternalConverter.mapperTransactionInternal(transactionInternalEntity);

    }

    private TransactionInternalEntity createTransactionInternal (TransactionInternalRequest transactionInternalRequest, PersonEntity peCrediteur, PersonEntity peDebiteur) {
        LocalDateTime date = LocalDateTime.now();
        TransactionInternalEntity transactionInternalEntity = new TransactionInternalEntity(0L,
                transactionInternalRequest.getDescription(),
                transactionInternalRequest.getAmount(),
                date.toString(),
                peCrediteur,
                peDebiteur);


        transactionInternalEntity = transactionInternalRepository.save(transactionInternalEntity);
        return transactionInternalEntity;
    }
    private void bankTransaction(PersonEntity crediteur, PersonEntity debiteur, Double amount) {
        BankEntity beCrediteur = crediteur.getBank();
        BankEntity beDebiteur  = debiteur.getBank();

        beCrediteur.setAmount(beCrediteur.getAmount() - amount);
        beDebiteur.setAmount (beDebiteur.getAmount()  + amount);

        bankRepository.save(beCrediteur);
        bankRepository.save(beDebiteur);
    }

    public TransactionInternal updateTransactionInternal(final Long id, TransactionInternalRequest transactionInternalRequest) {

        TransactionInternalEntity entity = transactionInternalRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Id " + id + " not found"));
        updateEntity(entity, transactionInternalRequest);
        entity = transactionInternalRepository.save(entity);
        return transactionInternalConverter.mapperTransactionInternal(entity);

    }

    public void deleteTransactionInternal(final Long id) {
        transactionInternalRepository.deleteById(id);
    }

    public void deleteTransactionInternals() {
        transactionInternalRepository.deleteAll();
    }

    private void updateEntity(TransactionInternalEntity transactionInternalEntity, TransactionInternalRequest transactionInternalRequest) {

        if (transactionInternalRequest.getDescription() != null)
            transactionInternalEntity.setDescription(transactionInternalRequest.getDescription());

        if (transactionInternalRequest.getAmount() != null)
            transactionInternalEntity.setAmount(transactionInternalRequest.getAmount());

        if (transactionInternalRequest.getTimeTransaction() != null)
            transactionInternalEntity.setTimeTransaction(transactionInternalRequest.getTimeTransaction());
    }



}
