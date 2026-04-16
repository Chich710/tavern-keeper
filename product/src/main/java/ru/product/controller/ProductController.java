package ru.product.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.product.dto.request.DiscountRequestDto;
import ru.product.dto.response.DiscountResponseDto;
import ru.product.service.DiscountService;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final DiscountService discountService;

    @GetMapping("/discount")
    public List<DiscountResponseDto> getDiscount(@RequestBody DiscountRequestDto request) {
        return discountService.getDiscounts(request);
    }
}
