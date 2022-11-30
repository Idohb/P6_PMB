package com.p6.paymybuddy.model.repository;

import com.p6.paymybuddy.model.entity.PersonEntity;
import com.p6.paymybuddy.model.entity.TransactionExternalEntity;
import com.p6.paymybuddy.model.entity.TransactionInternalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface TransactionExternalRepository extends JpaRepository<TransactionExternalEntity,Long> {
    Optional<List<TransactionExternalEntity>> findByUser(PersonEntity crediteur);

}
