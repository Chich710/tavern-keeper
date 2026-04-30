package me.rentapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OpenCageComponents {
    @JsonProperty("_normalized_city")
    private String normalizedCity;
    private String city;
    private String town;
    private String township;
    private String village;
    private String hamlet;
    private String municipality;
    private String suburb;
    private String county;
    private String island;
}
