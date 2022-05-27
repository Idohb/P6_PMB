package com.p6.paymybuddy.model.repository;

import com.p6.paymybuddy.model.entity.CommissionEntity;
import com.p6.paymybuddy.model.entity.TransactionInternalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommissionRepository extends JpaRepository<CommissionEntity,Long> {
    Optional<CommissionEntity> findByTransaction(TransactionInternalEntity transaction);
}
