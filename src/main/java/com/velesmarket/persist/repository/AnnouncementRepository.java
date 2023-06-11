package com.velesmarket.persist.repository;

import com.velesmarket.persist.entity.AnnouncementEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncementRepository extends JpaRepository<AnnouncementEntity, Long> {
    Page<AnnouncementEntity> findAll(Pageable page);
}
