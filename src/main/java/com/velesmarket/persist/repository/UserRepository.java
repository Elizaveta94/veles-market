package com.velesmarket.persist.repository;

import com.velesmarket.persist.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByLogin(String login);

    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByMobileNumber(Long mobileNumber);
}
