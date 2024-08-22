package com.mx.mcsv.service_auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mx.mcsv.service_auth.entity.AuthUser;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, Integer> {
	
    Optional<AuthUser> findByUserName(String username);
    
}
