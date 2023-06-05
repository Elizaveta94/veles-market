package com.velesmarket.service.mapper;

import com.velesmarket.domain.PhotoAnnouncementDto;
import com.velesmarket.persist.entity.PhotoAnnouncementEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Mapper(componentModel = "spring")
public interface PhotoAnnouncementMapper {
    @Mapping(target = "src", source = "file.bytes")
    PhotoAnnouncementEntity mapToEntity(MultipartFile file) throws IOException;

    PhotoAnnouncementDto mapToDto(PhotoAnnouncementEntity source);
}
