package com.velesmarket.persist.repository;

import com.velesmarket.persist.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    List<CategoryEntity> findAllByParentIsNull();

    Optional<CategoryEntity> findByTitle(String title);
}
