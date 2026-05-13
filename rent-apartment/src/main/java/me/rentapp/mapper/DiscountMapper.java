package me.rentapp.mapper;

import me.rentapp.dto.BookingRequestDto;
import me.rentapp.dto.DiscountRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface DiscountMapper {

    @Mapping(source = "request.checkIn", target = "checkIn")
    @Mapping(source = "request.checkOut", target = "checkOut")
    @Mapping(source = "request.userAge", target = "userAge")
    @Mapping(source = "activeBookingsCount", target = "activeBookingsCount")
    DiscountRequestDto toDiscountRequest(
            BookingRequestDto request,
            int activeBookingsCount
    );
}
