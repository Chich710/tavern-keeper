package ru.product.service.handler;

import ru.product.dto.request.DiscountRequestDto;
import ru.product.entity.DiscountEntity;

import java.util.List;

public interface DiscountHandler {
    List<DiscountEntity> getApplicable(DiscountRequestDto request);
}
