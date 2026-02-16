package me.authapp.mapper;

import me.authapp.dto.request.UserRegistrationRequestDto;
import me.authapp.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface UserMapper {
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "token", ignore = true)
    @Mapping(target = "roleEntity", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    UserEntity userRegistrationRequestDtoToUserEntity(UserRegistrationRequestDto userRegistrationRequestDto);
}
