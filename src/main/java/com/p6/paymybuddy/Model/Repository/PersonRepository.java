package com.p6.paymybuddy.Model.Repository;

import com.p6.paymybuddy.Model.Entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository  extends JpaRepository<PersonEntity, Long> {

}
