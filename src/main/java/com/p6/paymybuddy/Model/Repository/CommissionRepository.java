package com.p6.paymybuddy.Model.Repository;

import com.p6.paymybuddy.Model.Entity.CommissionEntity;
import com.p6.paymybuddy.Model.Entity.TransactionInternalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommissionRepository extends JpaRepository<CommissionEntity,Long> {
    Optional<CommissionEntity> findByTransaction(TransactionInternalEntity transaction);
}
