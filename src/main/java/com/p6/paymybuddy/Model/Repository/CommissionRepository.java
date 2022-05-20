package com.p6.paymybuddy.Model.Repository;

import com.p6.paymybuddy.Model.Entity.CommissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommissionRepository extends JpaRepository<CommissionEntity,Long> {
    Optional<CommissionEntity> findByTransactionid(Long transaction_id);
}
