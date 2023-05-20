package com.velesmarket.service.mapper;

import com.velesmarket.domain.AnnouncementDto;
import com.velesmarket.persist.entity.AnnouncementEntity;
import com.velesmarket.persist.entity.UserEntity;
import lombok.SneakyThrows;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.io.IOException;

@Mapper(componentModel = "spring", uses = {PhotoAnnouncementMapper.class, CategoryMapper.class, LocationMapper.class})
public interface AnnouncementMapper {
    @Mapping(target = "user", source = "userEntity")
    @Mapping(target = "id", source = "source.id")
    @Mapping(target = "title", source = "source.title")
    @Mapping(target = "description", source = "source.description")
    @Mapping(target = "cost", source = "source.cost")
    @Mapping(target = "photosAnnouncement", source = "source.photosAnnouncement")
    @Mapping(target = "location", source = "source.location")
    @Mapping(target = "category", source = "source.category")
    AnnouncementEntity mapToEntity(AnnouncementDto source, UserEntity userEntity) throws IOException;
}
