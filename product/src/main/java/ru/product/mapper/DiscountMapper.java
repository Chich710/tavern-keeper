package ru.product.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.product.dto.response.DiscountResponseDto;
import ru.product.entity.DiscountEntity;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface DiscountMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "discountPercent", target = "discountPercent")
    @Mapping(source = "conditionType", target = "conditionType")
    @Mapping(source = "conditionValue", target = "conditionValue")
    @Mapping(source = "startDate", target = "startDate")
    @Mapping(source = "endDate", target = "endDate")
    DiscountResponseDto toDiscountResponseDto(DiscountEntity entity);
}
