package com.velesmarket.service.mapper;

import com.velesmarket.domain.CategoryDto;
import com.velesmarket.persist.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto mapToDto(CategoryEntity source);

    @Mapping(target = "announcements", ignore = true)
    CategoryEntity mapToEntity(CategoryDto source);

    List<CategoryDto> mapToDto(List<CategoryEntity> source);
}
