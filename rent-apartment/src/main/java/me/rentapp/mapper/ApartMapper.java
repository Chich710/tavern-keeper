package me.rentapp.mapper;

import me.rentapp.dto.AddressCreateRequestDto;
import me.rentapp.dto.ApartCreateRequestDto;
import me.rentapp.dto.ApartResponseDto;
import me.rentapp.entity.AddressApartEntity;
import me.rentapp.entity.ApartEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, imports = java.time.LocalDate.class)
public interface ApartMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", expression = "java(LocalDate.now())")
    @Mapping(target = "updatedAt", expression = "java(LocalDate.now())")
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "addressApart", expression = "java(toAddressEntity(dto.getAddress()))")
    ApartEntity toNewApartEntity(ApartCreateRequestDto dto);

    @Mapping(source = "apart.id", target = "id")
    @Mapping(source = "apart.title", target = "title")
    @Mapping(source = "apart.description", target = "description")
    @Mapping(source = "apart.pricePerNight", target = "pricePerNight")
    @Mapping(source = "apart.rooms", target = "rooms")
    @Mapping(source = "apart.createdAt", target = "createdAt")
    @Mapping(source = "address.city", target = "city")
    @Mapping(source = "address.street", target = "street")
    @Mapping(source = "address.houseNumber", target = "houseNumber")
    @Mapping(source = "address.floor", target = "floor")
    @Mapping(source = "address.apartmentNumber", target = "apartmentNumber")
    ApartResponseDto toApartResponseDto(ApartEntity apart, AddressApartEntity address);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "apart", ignore = true)
    AddressApartEntity toAddressEntity(AddressCreateRequestDto dto);
}
