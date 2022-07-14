package com.p6.paymybuddy.model.repository;

import com.p6.paymybuddy.model.entity.PersonEntity;
import com.p6.paymybuddy.model.entity.TransactionExternalEntity;
import com.p6.paymybuddy.model.entity.TransactionInternalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionExternalRepository extends JpaRepository<TransactionExternalEntity,Long> {
    Optional<List<TransactionExternalEntity>> findByUser(PersonEntity crediteur);

}
