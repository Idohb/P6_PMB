package com.p6.paymybuddy.model.repository;

import com.p6.paymybuddy.model.entity.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<LoginEntity,Long> {
    Optional<LoginEntity> findByEmailAndPassword(String email, String password);
    Optional<LoginEntity> findByEmail(String email);
}
