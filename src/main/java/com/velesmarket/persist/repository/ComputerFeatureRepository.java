package com.velesmarket.persist.repository;

import com.velesmarket.persist.entity.AutoFeatureEntity;
import com.velesmarket.persist.entity.ComputerFeatureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ComputerFeatureRepository extends JpaRepository<ComputerFeatureEntity, Long> {
    Optional<ComputerFeatureEntity> findByAnnouncementId(Long id);
}
