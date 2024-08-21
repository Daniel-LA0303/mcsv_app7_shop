package com.mx.mcsv.service_user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mx.mcsv.service_user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
