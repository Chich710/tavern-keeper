package me.rentapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BookingResponseDto {
    private Integer id;
    private Long apartmentId;
    private String apartmentTitle;
    private Long userId;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private Integer pricePerNight;
    private Integer discountPercent;
    private Integer totalPrice;
    private LocalDate createdAt;
}
