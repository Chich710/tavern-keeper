package me.rentapp.service.impl;

import lombok.RequiredArgsConstructor;
import me.rentapp.client.ProductClient;
import me.rentapp.dto.BookingRequestDto;
import me.rentapp.dto.BookingResponseDto;
import me.rentapp.dto.DiscountDto;
import me.rentapp.dto.DiscountRequestDto;
import me.rentapp.entity.ApartEntity;
import me.rentapp.entity.BookingEntity;
import me.rentapp.mapper.BookingMapper;
import me.rentapp.mapper.DiscountMapper;
import me.rentapp.repository.ApartRepository;
import me.rentapp.repository.BookingRepository;
import me.rentapp.service.BookingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final ApartRepository apartRepository;
    private final ProductClient productClient;
    private final BookingMapper bookingMapper;
    private final DiscountMapper discountMapper;

    @Override
    @Transactional
    public BookingResponseDto createBooking(BookingRequestDto request) {
        ApartEntity apartment = apartRepository.findById(request.getApartmentId().intValue())
                .orElseThrow(() -> new RuntimeException("Apartment not found: " + request.getApartmentId()));

        long nights = ChronoUnit.DAYS.between(request.getCheckIn(), request.getCheckOut());
        if (nights <= 0) {
            throw new RuntimeException("Check out date must be after check in date");
        }

        long activeBookings = bookingRepository.countActiveBookings(request.getUserId(), LocalDate.now());

        int activeBookingsIncludingNew = (int) activeBookings + 1;
        DiscountRequestDto discountRequest = discountMapper.toDiscountRequest(request, activeBookingsIncludingNew);

        List<DiscountDto> discounts;
        try {
            discounts = productClient.getDiscounts(discountRequest);
        } catch (RestClientException e) {
            throw new RuntimeException("Failed to fetch discounts from product service", e);
        }
        if (discounts == null) {
            discounts = List.of();
        }

        int totalDiscountPercent = Math.min(
                discounts.stream()
                        .map(DiscountDto::getDiscountPercent)
                        .filter(Objects::nonNull)
                        .mapToInt(Integer::intValue)
                        .sum(),
                80
        );

        int pricePerNight = apartment.getPricePerNight();
        int baseTotal = pricePerNight * (int) nights;
        int totalPrice = baseTotal - (baseTotal * totalDiscountPercent / 100);

        BookingEntity booking = bookingMapper.toBookingEntity(request, apartment, pricePerNight, totalDiscountPercent, totalPrice);
        BookingEntity newBooking = bookingRepository.save(booking);

        return bookingMapper.toBookingResponseDto(newBooking, apartment);
    }
}
