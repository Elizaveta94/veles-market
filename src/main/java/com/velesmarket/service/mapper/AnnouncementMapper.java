package com.velesmarket.service.mapper;

import com.velesmarket.domain.AnnouncementDto;
import com.velesmarket.domain.CategoryDto;
import com.velesmarket.persist.entity.CategoryEntity;
import com.velesmarket.persist.entity.LocationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnnouncementMapper {
    AnnouncementDto mapToDto(LocationEntity source);

    AnnouncementDto mapToDto(CategoryEntity source);

}
