package com.velesmarket.service.mapper;

import com.velesmarket.domain.LocationDto;
import com.velesmarket.persist.entity.LocationEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocationMapper {
    LocationEntity mapToEntity(LocationDto source);
}
