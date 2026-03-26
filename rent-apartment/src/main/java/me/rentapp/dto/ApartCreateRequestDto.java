package me.rentapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApartCreateRequestDto {
    private String title;
    private String description;
    private Integer pricePerNight;
    private Integer rooms;
    private AddressCreateRequestDto address;
}
