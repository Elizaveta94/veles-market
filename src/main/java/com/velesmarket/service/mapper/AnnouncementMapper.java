package com.velesmarket.service.mapper;

import com.velesmarket.domain.AnnouncementDto;
import com.velesmarket.persist.entity.AnnouncementEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnnouncementMapper {
    AnnouncementEntity mapToEntity(AnnouncementDto source);
}
