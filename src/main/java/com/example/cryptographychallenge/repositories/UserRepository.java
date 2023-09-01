package com.example.cryptographychallenge.repositories;

import com.example.cryptographychallenge.entites.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
