package com.p6.paymybuddy.Service;

import com.p6.paymybuddy.Controller.Dto.TransactionInternal.TransactionInternalRequest;
import com.p6.paymybuddy.Mapper.TransactionInternalConverter;
import com.p6.paymybuddy.Model.Entity.TransactionInternalEntity;
import com.p6.paymybuddy.Model.Repository.TransactionInternalRepository;
import com.p6.paymybuddy.Service.Data.TransactionInternal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class TransactionInternalService {


    // Add constructor
    @Autowired
    private TransactionInternalConverter transactionInternalConverter;
    @Autowired
    private TransactionInternalRepository transactionInternalRepository;

    public TransactionInternalService() {
    }

    public List<TransactionInternal> getTransactionInternals() {
        return transactionInternalConverter.mapperTransactionInternal( transactionInternalRepository.findAll());
    }

    public TransactionInternal getTransactionInternal(final Long id) {
        TransactionInternalEntity transactionInternalEntity = transactionInternalRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Id " + id + " not found"));
        return transactionInternalConverter.mapperTransactionInternal(transactionInternalEntity);
    }


    public TransactionInternal addTransactionInternal(TransactionInternalRequest transactionInternalRequest) {
        TransactionInternalEntity transactionInternalEntity = new TransactionInternalEntity(0L,
                transactionInternalRequest.getDescription(),
                transactionInternalRequest.getAmount(),
                transactionInternalRequest.getTimeTransaction(),
                transactionInternalRequest.getCrediteur(),
                transactionInternalRequest.getDebiteur());
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

        if (transactionInternalRequest.getCrediteur() != null)
            transactionInternalEntity.setCrediteur(transactionInternalRequest.getCrediteur());

        if (transactionInternalRequest.getDebiteur() != null)
            transactionInternalEntity.setDebiteur(transactionInternalRequest.getDebiteur());

    }


//    private Map<String, Object> matchPersonInfoByFirstnameAndLastname(List<TransactionInternalEntity> transactionInternalEntityList) {
//        Map<String, Object> map = new HashMap<>();
//
//        for (TransactionInternalEntity le : transactionInternalEntityList) {
//
//        }
//
//        return map;
//    }


}
