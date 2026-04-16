package ru.product.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DiscountResponseDto {
    private Integer id;
    private String name;
    private String description;
    private Integer discountPercent;
    private String conditionType;
    private String conditionValue;
    private LocalDate startDate;
    private LocalDate endDate;
}
