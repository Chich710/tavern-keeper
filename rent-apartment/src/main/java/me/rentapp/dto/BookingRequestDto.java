package me.rentapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BookingRequestDto {
    private Long apartmentId;
    private Long userId;
    private Integer userAge;
    private LocalDate checkIn;
    private LocalDate checkOut;
}
