package com.velesmarket.service.mapper;

import com.velesmarket.persist.entity.PhotoAnnouncementEntity;
import lombok.SneakyThrows;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Mapper(componentModel = "spring")
public interface PhotoAnnouncementMapper {
    @Mapping(target = "src", source = "file.bytes")
    PhotoAnnouncementEntity mapToEntity(MultipartFile file) throws IOException;
}
