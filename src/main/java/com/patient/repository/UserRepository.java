package com.patient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.patient.entity.UserDtls;

public interface UserRepository extends JpaRepository<UserDtls, Integer> {

    boolean existsUserDtlsByEmail(String email);

    UserDtls findByEmail(String email);

	UserDtls findById(Long id);
}
