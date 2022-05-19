package com.p6.paymybuddy.Service;

import com.p6.paymybuddy.Controller.Dto.Commission.CommissionRequest;
import com.p6.paymybuddy.Mapper.CommissionConverter;
import com.p6.paymybuddy.Model.Entity.CommissionEntity;
import com.p6.paymybuddy.Model.Repository.CommissionRepository;
import com.p6.paymybuddy.Service.Data.Commission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CommissionService {

    @Autowired
    private CommissionConverter commissionConverter;

    @Autowired
    private CommissionRepository commissionRepository;

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
        CommissionEntity commissionEntity = new CommissionEntity(0L,
                commissionRequest.getId_transaction(),
                commissionRequest.getAmount()
        );
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
            commissionEntity.setTransaction_id(commissionRequest.getTransaction_id());

        if (commissionRequest.getAmount() != null)
            commissionEntity.setAmount(commissionRequest.getAmount());
    }

    public Commission searchTransaction(Long transaction_id) {
        return commissionConverter.mapperCommission(commissionRepository.findByTransaction_id(transaction_id)
                .orElseThrow(() -> new NoSuchElementException("")));
    }

}
