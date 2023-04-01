package com.velesmarket.persist.repository;

import com.velesmarket.persist.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
