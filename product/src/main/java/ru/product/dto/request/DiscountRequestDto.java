package ru.product.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DiscountRequestDto {
    private LocalDate checkIn;
    private LocalDate checkOut;
    private Integer userAge;
    private Integer activeBookingsCount;
}
