package com.p6.paymybuddy.Model.Repository;

import com.p6.paymybuddy.Model.Entity.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<LoginEntity,Long> {
}
