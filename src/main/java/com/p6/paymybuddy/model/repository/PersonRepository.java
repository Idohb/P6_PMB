package com.p6.paymybuddy.model.repository;

import com.p6.paymybuddy.model.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository  extends JpaRepository<PersonEntity, Long> {

}
