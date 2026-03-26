package me.rentapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressCreateRequestDto {
    private String city;
    private String street;
    private String houseNumber;
    private Integer floor;
    private String apartmentNumber;
}
