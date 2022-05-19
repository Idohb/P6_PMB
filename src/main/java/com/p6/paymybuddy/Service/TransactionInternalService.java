package com.p6.paymybuddy.Service;

import com.p6.paymybuddy.Controller.Dto.TransactionInternal.TransactionInternalRequest;
import com.p6.paymybuddy.Mapper.PersonConverter;
import com.p6.paymybuddy.Mapper.TransactionInternalConverter;
import com.p6.paymybuddy.Model.Entity.PersonEntity;
import com.p6.paymybuddy.Model.Entity.TransactionInternalEntity;
import com.p6.paymybuddy.Model.Repository.PersonRepository;
import com.p6.paymybuddy.Model.Repository.TransactionInternalRepository;
import com.p6.paymybuddy.Service.Data.TransactionInternal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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
    private PersonConverter personConverter;

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
        List<TransactionInternalEntity> transactionInternalEntity = transactionInternalRepository.findByCrediteurIdPerson(id).orElseThrow(() -> new NoSuchElementException("Id " + id + " not found"));
        return transactionInternalConverter.mapperTransactionInternal(transactionInternalEntity);
    }

    public TransactionInternal addTransactionInternal(TransactionInternalRequest transactionInternalRequest) {
        List<PersonEntity> personEntities = personRepository.findAll();
        LocalDateTime date = LocalDateTime.now();
        PersonEntity peCrediteur = personRepository.findById(transactionInternalRequest.getCrediteur()).orElseThrow(() -> new NoSuchElementException("Id Crediteur : " + transactionInternalRequest.getCrediteur() + " not found"));
        PersonEntity peDebiteur  = personRepository.findById(transactionInternalRequest.getDebiteur()).orElseThrow(()  -> new NoSuchElementException("Id Debiteur : "  + transactionInternalRequest.getDebiteur()  + " not found"));

        TransactionInternalEntity transactionInternalEntity = new TransactionInternalEntity(0L,
                transactionInternalRequest.getDescription(),
                transactionInternalRequest.getAmount(),
                date.toString(),
                peCrediteur,
                peDebiteur);

        transactionInternalEntity = transactionInternalRepository.save(transactionInternalEntity);
        return transactionInternalConverter.mapperTransactionInternal(transactionInternalEntity);

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
            transactionInternalEntity.setTimeTransactionInternal(transactionInternalRequest.getTimeTransaction());
    }





//    @Transactional
//    public void doTransaction(Long idCrediteur, Long idDebiteur, double amount) {
//        PersonEntity credit = personRepository.findById(idCrediteur).orElseThrow(() -> new NoSuchElementException("Id Crediteur : " + id + " not found"));
//        PersonEntity debit = personRepository.findById(idDebiteur).orElseThrow(() -> new NoSuchElementException("Id Debiteur : " + id + " not found"));
//        double commission = amount * 0.05;
//
//        credit.setAmount(credit.getAmount() + amount);
//        debit.setAmount(debit.getAmount() - amount - commission);
//        personRepository.save(credit);
//        personRepository.save(debit);
//        this.addTransaction(credit, debit, amount, commission);
//    }



}
