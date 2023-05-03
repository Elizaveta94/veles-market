package com.velesmarket.service.mapper;

import com.velesmarket.domain.UserDto;
import com.velesmarket.persist.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "password", ignore = true)
    UserEntity mapToEntity(UserDto source);

    @Mapping(target = "password", ignore = true)
    UserDto mapToDto(UserEntity source);

}
