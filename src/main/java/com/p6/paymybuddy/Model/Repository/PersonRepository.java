package com.p6.paymybuddy.Model.Repository;

import com.p6.paymybuddy.Model.Entity.LoginEntity;
import com.p6.paymybuddy.Model.Entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository  extends JpaRepository<PersonEntity, Long> {
    Optional<PersonEntity> findByFirstNameAndLastName(String firstName, String lastName);
//    Optional<PersonEntity> findByLogin_Email(String email);
}
