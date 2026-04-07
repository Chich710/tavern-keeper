package me.authapp.mapper;

import me.authapp.dto.request.UserRegistrationRequestDto;
import me.authapp.entity.RoleEntity;
import me.authapp.entity.UserEntity;
import me.authapp.util.EncodeDecodeUtil;
import me.authapp.util.TokenUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, imports = {EncodeDecodeUtil.class, TokenUtil.class, java.time.LocalDate.class})
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", expression = "java(EncodeDecodeUtil.encodePassword(dto.getPassword()))")
    @Mapping(target = "roleEntity", source = "roleEntity")
    @Mapping(target = "token", expression = "java(TokenUtil.generateGuestToken(roleEntity.getRoleCode()))")
    @Mapping(target = "createdAt", expression = "java(LocalDate.now())")
    @Mapping(target = "updatedAt", expression = "java(LocalDate.now())")
    @Mapping(target = "deletedAt", ignore = true)
    UserEntity userRegistrationRequestDtoToUserEntity(UserRegistrationRequestDto dto, RoleEntity roleEntity);
}
