package com.velesmarket.service.mapper;

import com.velesmarket.domain.PhotoAnnouncementDto;
import com.velesmarket.persist.entity.PhotoAnnouncementEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Mapper(componentModel = "spring")
public interface PhotoAnnouncementMapper {
    @Mapping(target = "src", expression="java(file.getBytes().length == 0 ? null : file.getBytes())")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "announcement", ignore = true)
    PhotoAnnouncementEntity mapToEntity(MultipartFile file) throws IOException;

    PhotoAnnouncementDto mapToDto(PhotoAnnouncementEntity source);
}
