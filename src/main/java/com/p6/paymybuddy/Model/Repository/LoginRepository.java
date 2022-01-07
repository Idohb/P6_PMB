package com.p6.paymybuddy.Model.Repository;

import com.p6.paymybuddy.Model.Entity.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<LoginEntity,Long> {
    Optional<LoginEntity> findByEmailAndPassword(String email, String password);
}
