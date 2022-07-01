package com.p6.paymybuddy.service;

import com.p6.paymybuddy.controller.dto.commission.CommissionRequest;
import com.p6.paymybuddy.mapper.CommissionConverter;
import com.p6.paymybuddy.model.entity.BankEntity;
import com.p6.paymybuddy.model.entity.CommissionEntity;
import com.p6.paymybuddy.model.entity.PersonEntity;
import com.p6.paymybuddy.model.entity.TransactionInternalEntity;
import com.p6.paymybuddy.model.repository.BankRepository;
import com.p6.paymybuddy.model.repository.CommissionRepository;
import com.p6.paymybuddy.model.repository.TransactionInternalRepository;
import com.p6.paymybuddy.service.data.Bank;
import com.p6.paymybuddy.service.data.Commission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CommissionService {

    @Autowired
    private CommissionConverter commissionConverter;

    @Autowired
    private CommissionRepository commissionRepository;

    @Autowired
    private TransactionInternalRepository transactionInternalRepository;

    @Autowired
    private BankRepository bankRepository;

    public CommissionService() {

    }

    public List<Commission> getCommissions() {
        return commissionConverter.mapperCommission(commissionRepository.findAll());
    }

    public Commission getCommission(final Long id) {
        CommissionEntity commissionEntity = commissionRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Id " + id + " not found"));
        return commissionConverter.mapperCommission(commissionEntity);
    }

//    public Commission addCommission(CommissionRequest commissionRequest) {
//        TransactionInternalEntity ti = transactionInternalRepository.findById(commissionRequest.getTransactionid())
//                .orElseThrow(() ->new NoSuchElementException("Transaction not found"));
//        double amountTransactionInternal = Double.parseDouble(commissionRequest.getAmount()) * 0.05;
//        BigDecimal commission = new BigDecimal(amountTransactionInternal).setScale(2, RoundingMode.HALF_UP);
//
//        CommissionEntity commissionEntity = new CommissionEntity(
//                0L,
//                commissionRequest.getTransactionid(),
//                commissionRequest.getAmount());
//
//
//        commissionEntity = commissionRepository.save(commissionEntity);
//
//        return commissionConverter.mapperCommission(commissionEntity);
//    }

    public Commission processCommission(Long transactionId, PersonEntity crediteur) {


        TransactionInternalEntity ti = transactionInternalRepository.findById(transactionId)
                .orElseThrow(() ->new NoSuchElementException("Transaction not found"));

        double amountTransactionInternal = ti.getAmount() * 0.05;
        BigDecimal commission = new BigDecimal(amountTransactionInternal).setScale(2, RoundingMode.HALF_UP);
        Double commissionAmount = commission.doubleValue();

        CommissionEntity commissionEntity = new CommissionEntity(
                0L,
                ti,
                commissionAmount
        );

        this.takeMoneyFromBank(crediteur, commissionAmount);

        commissionRepository.save(commissionEntity);

        return commissionConverter.mapperCommission(commissionEntity);
    }

    private void takeMoneyFromBank(PersonEntity crediteur, Double amount) {
        BankEntity bankEntity = bankRepository.findByPerson_Id(crediteur.getId())
                .orElseThrow(() ->new NoSuchElementException("Transaction not found"));

        Double takeMoneyForApp = bankEntity.getAmount() - amount;
        bankEntity.setAmount(takeMoneyForApp);

        bankRepository.save(bankEntity);

    }

    public Commission addCommission(Long transactionId) {
        TransactionInternalEntity ti = transactionInternalRepository.findById(transactionId)
                .orElseThrow(() ->new NoSuchElementException("Transaction not found"));

        double amountTransactionInternal = ti.getAmount() * 0.05;
        BigDecimal commission = new BigDecimal(amountTransactionInternal).setScale(2, RoundingMode.HALF_UP);

        CommissionEntity commissionEntity = new CommissionEntity(
                0L,
                ti,
                commission.doubleValue()
        );

        commissionRepository.save(commissionEntity);

        return commissionConverter.mapperCommission(commissionEntity);
    }

    public Commission updateCommission(final Long id, CommissionRequest commissionRequest) {
        CommissionEntity commissionEntity = commissionRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Id " + id + " not found"));
        this.updateCommission(commissionEntity, commissionRequest);
        commissionEntity = commissionRepository.save(commissionEntity);
        return commissionConverter.mapperCommission(commissionEntity);
    }

    private void updateCommission(CommissionEntity commissionEntity, CommissionRequest commissionRequest) {
        if (commissionRequest.getTransactionId() != null)
            commissionEntity.setTransaction(transactionInternalRepository.findById(commissionRequest.getTransactionId())
                    .orElseThrow(() -> new NoSuchElementException("")));

        commissionEntity.setAmount(commissionRequest.getAmount());
    }

    public Commission searchCommissionByTransaction(Long transactionId) {
        TransactionInternalEntity transaction = transactionInternalRepository.findById(transactionId)
                .orElseThrow(() -> new NoSuchElementException(""));
        return commissionConverter.mapperCommission(commissionRepository.findByTransaction(transaction)
                .orElseThrow(() -> new NoSuchElementException("")));
    }

}
