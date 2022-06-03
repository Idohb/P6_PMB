package com.p6.paymybuddy.model.repository;

import com.p6.paymybuddy.model.entity.BankEntity;
import com.p6.paymybuddy.model.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface BankRepository extends JpaRepository<BankEntity, Long> {

    Optional<BankEntity> findByPerson(PersonEntity personId);
    Optional<BankEntity> findByPerson_Id(Long id);
}
