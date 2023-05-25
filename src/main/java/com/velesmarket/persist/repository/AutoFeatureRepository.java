package com.velesmarket.persist.repository;

import com.velesmarket.persist.entity.AutoFeatureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AutoFeatureRepository extends JpaRepository<AutoFeatureEntity, Long> {
    Optional<AutoFeatureEntity> findByAnnouncementId(Long id);
}
