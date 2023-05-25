package com.velesmarket.persist.repository;

import com.velesmarket.persist.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<LocationEntity, Long> {
    Optional<LocationEntity> findByCityAndStreet(String city, String street);
}
