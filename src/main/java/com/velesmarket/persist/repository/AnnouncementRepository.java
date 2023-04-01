package com.velesmarket.persist.repository;

import com.velesmarket.persist.entity.AnnouncementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncementRepository extends JpaRepository<AnnouncementEntity, Long> {
}
