package me.rentapp.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class DiscountDto {
    private Integer id;
    private String name;
    private String description;
    private Integer discountPercent;
    private String conditionType;
    private String conditionValue;
    private LocalDate startDate;
    private LocalDate endDate;
}
