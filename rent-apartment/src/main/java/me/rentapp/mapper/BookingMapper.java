package me.rentapp.mapper;

import me.rentapp.dto.BookingRequestDto;
import me.rentapp.dto.BookingResponseDto;
import me.rentapp.entity.ApartEntity;
import me.rentapp.entity.BookingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, imports = java.time.LocalDate.class)
public interface BookingMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "apartment", target = "apartment")
    @Mapping(source = "request.userId", target = "userId")
    @Mapping(source = "request.checkIn", target = "checkIn")
    @Mapping(source = "request.checkOut", target = "checkOut")
    @Mapping(source = "pricePerNight", target = "pricePerNight")
    @Mapping(source = "discountPercent", target = "discountPercent")
    @Mapping(source = "totalPrice", target = "totalPrice")
    @Mapping(target = "createdAt", expression = "java(LocalDate.now())")
    @Mapping(target = "updatedAt", expression = "java(LocalDate.now())")
    @Mapping(target = "deletedAt", ignore = true)
    BookingEntity toBookingEntity(BookingRequestDto request, ApartEntity apartment,
                                  Integer pricePerNight, Integer discountPercent, Integer totalPrice);

    @Mapping(source = "booking.id", target = "id")
    @Mapping(source = "apartment.id", target = "apartmentId")
    @Mapping(source = "apartment.title", target = "apartmentTitle")
    @Mapping(source = "booking.userId", target = "userId")
    @Mapping(source = "booking.checkIn", target = "checkIn")
    @Mapping(source = "booking.checkOut", target = "checkOut")
    @Mapping(source = "booking.pricePerNight", target = "pricePerNight")
    @Mapping(source = "booking.discountPercent", target = "discountPercent")
    @Mapping(source = "booking.totalPrice", target = "totalPrice")
    @Mapping(source = "booking.createdAt", target = "createdAt")
    BookingResponseDto toBookingResponseDto(BookingEntity booking, ApartEntity apartment);
}
