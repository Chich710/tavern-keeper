package ru.product.service.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.product.dto.request.DiscountRequestDto;
import ru.product.entity.DiscountEntity;
import ru.product.repository.DiscountRepository;
import java.util.List;
import static java.util.Collections.emptyList;

@Component
@RequiredArgsConstructor
public class AgeDiscountHandler implements DiscountHandler {
    private final DiscountRepository discountRepository;

    @Override
    public List<DiscountEntity> getApplicable(DiscountRequestDto request) {
        if (request.getUserAge() == null) {

            return emptyList();
        }

        return discountRepository.getAgeDiscounts()
                .stream()
                .filter(dist -> dist.getConditionValue() != null
                        && request.getUserAge() < Integer.parseInt(dist.getConditionValue()))
                .toList();
    }
}
