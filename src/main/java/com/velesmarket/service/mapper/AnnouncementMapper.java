package com.velesmarket.service.mapper;

import com.velesmarket.domain.AnnouncementCardDto;
import com.velesmarket.domain.AnnouncementCreateRequest;
import com.velesmarket.domain.AnnouncementDto;
import com.velesmarket.persist.entity.AnnouncementEntity;
import com.velesmarket.persist.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.io.IOException;
import java.util.Map;

@Mapper(componentModel = "spring", uses = {PhotoAnnouncementMapper.class, CategoryMapper.class, LocationMapper.class, UserMapper.class})
public interface AnnouncementMapper {
    long NO_CONTENT_IMG = 1L;

    @Mapping(target = "user", source = "userEntity")
    @Mapping(target = "id", source = "source.id")
    @Mapping(target = "title", source = "source.title")
    @Mapping(target = "description", source = "source.description")
    @Mapping(target = "cost", source = "source.cost")
    @Mapping(target = "photosAnnouncement", source = "source.photosAnnouncement")
    @Mapping(target = "location", source = "source.location")
    @Mapping(target = "category", source = "source.category")
    AnnouncementEntity mapToEntity(AnnouncementCreateRequest source, UserEntity userEntity) throws IOException;

    @Mapping(target = "featureMap", source = "featureMap")
    @Mapping(target = "user", source = "source.user")
    @Mapping(target = "id", source = "source.id")
    @Mapping(target = "title", source = "source.title")
    @Mapping(target = "description", source = "source.description")
    @Mapping(target = "cost", source = "source.cost")
    @Mapping(target = "photosAnnouncement", source = "source.photosAnnouncement")
    @Mapping(target = "location", source = "source.location")
    @Mapping(target = "category", source = "source.category")
    AnnouncementDto mapToDto(AnnouncementEntity source, Map<String, String> featureMap);

    default AnnouncementCardDto mapToCardDto(AnnouncementEntity source) {
        return AnnouncementCardDto.builder()
                .id(source.getId())
                .title(source.getTitle())
                .cost(source.getCost())
                .photoId(source.getPhotosAnnouncement().isEmpty() ? NO_CONTENT_IMG : source.getPhotosAnnouncement().get(0).getId())
                .build();
    }
}
