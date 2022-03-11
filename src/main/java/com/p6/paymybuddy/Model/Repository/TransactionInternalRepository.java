package com.p6.paymybuddy.Model.Repository;

import com.p6.paymybuddy.Model.Entity.PersonEntity;
import com.p6.paymybuddy.Model.Entity.TransactionInternalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionInternalRepository extends JpaRepository<TransactionInternalEntity,Long> {
    Optional<List<TransactionInternalEntity>> findByCrediteurIdPerson(Long idPerson);
}