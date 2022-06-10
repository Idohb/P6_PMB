package com.p6.paymybuddy.model.repository;

import com.p6.paymybuddy.model.entity.TransactionExternalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionExternalRepository extends JpaRepository<TransactionExternalEntity,Long> {
}
