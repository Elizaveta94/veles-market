package com.velesmarket.service.impl;

import com.velesmarket.domain.CategoryDto;
import com.velesmarket.persist.entity.CategoryEntity;
import com.velesmarket.persist.repository.CategoryRepository;
import com.velesmarket.service.CategoryService;
import com.velesmarket.service.mapper.CategoryMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryDto> getAll() {
        List<CategoryEntity> categoryEntities = categoryRepository.findAllByParentIsNull();
        return categoryMapper.mapToDto(categoryEntities);
    }
}
