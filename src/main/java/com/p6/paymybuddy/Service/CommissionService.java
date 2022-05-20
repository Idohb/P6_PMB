package com.p6.paymybuddy.Service;

import com.p6.paymybuddy.Controller.Dto.Commission.CommissionRequest;
import com.p6.paymybuddy.Mapper.CommissionConverter;
import com.p6.paymybuddy.Model.Entity.CommissionEntity;
import com.p6.paymybuddy.Model.Entity.TransactionInternalEntity;
import com.p6.paymybuddy.Model.Repository.CommissionRepository;
import com.p6.paymybuddy.Model.Repository.TransactionInternalRepository;
import com.p6.paymybuddy.Service.Data.Commission;
import com.p6.paymybuddy.Service.Data.TransactionInternal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
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

    public CommissionService() {

    }

    public List<Commission> getCommissions() {
        return commissionConverter.mapperCommission(commissionRepository.findAll());
    }

    public Commission getCommission(final Long id) {
        CommissionEntity commissionEntity = commissionRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Id " + id + " not found"));
        return commissionConverter.mapperCommission(commissionEntity);
    }

    public Commission addCommission(CommissionRequest commissionRequest) {
        TransactionInternalEntity ti = transactionInternalRepository.findById(commissionRequest.getId_transaction())
                .orElseThrow(() ->new NoSuchElementException("Transaction not found"));

        CommissionEntity commissionEntity = new CommissionEntity(
                0L,
                commissionRequest.getId_transaction(),
                ""
        );

        double amountTransactionInternal = Double.parseDouble(ti.getAmount()) * 0.05;
        BigDecimal commission = new BigDecimal(amountTransactionInternal).setScale(2, RoundingMode.HALF_UP);
        commissionEntity.setAmount(commission.toString());

        commissionRepository.save(commissionEntity);

        return commissionConverter.mapperCommission(commissionEntity);
    }

    public Commission addCommission(Long transactionId) {
        TransactionInternalEntity ti = transactionInternalRepository.findById(transactionId)
                .orElseThrow(() ->new NoSuchElementException("Transaction not found"));

        double amountTransactionInternal = Double.parseDouble(ti.getAmount()) * 0.05;
        BigDecimal commission = new BigDecimal(amountTransactionInternal).setScale(2, RoundingMode.HALF_UP);

        CommissionEntity commissionEntity = new CommissionEntity(
                0L,
                transactionId,
                commission.toString()
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
        if (commissionRequest.getTransaction_id() != null)
            commissionEntity.setTransactionid(commissionRequest.getTransaction_id());

        commissionEntity.setAmount(commissionRequest.getAmount());
    }

    public Commission searchTransaction(Long transaction_id) {
        return commissionConverter.mapperCommission(commissionRepository.findByTransactionid(transaction_id)
                .orElseThrow(() -> new NoSuchElementException("")));
    }

}
