package me.rentapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ApartResponseDto {
    private Long id;
    private String title;
    private String description;
    private Integer pricePerNight;
    private Integer rooms;
    private String city;
    private String street;
    private String houseNumber;
    private Integer floor;
    private String apartmentNumber;
    private LocalDate createdAt;
}
