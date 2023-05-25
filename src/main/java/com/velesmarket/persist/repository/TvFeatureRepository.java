package com.velesmarket.persist.repository;

import com.velesmarket.persist.entity.AutoFeatureEntity;
import com.velesmarket.persist.entity.TvFeatureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TvFeatureRepository extends JpaRepository<TvFeatureEntity, Long> {
    Optional<TvFeatureEntity> findByAnnouncementId(Long id);
}
