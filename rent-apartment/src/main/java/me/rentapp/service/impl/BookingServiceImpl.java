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
import me.rentapp.repository.ApartRepository;
import me.rentapp.repository.BookingRepository;
import me.rentapp.service.BookingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final ApartRepository apartRepository;
    private final ProductClient productClient;
    private final BookingMapper bookingMapper;

    @Override
    @Transactional
    public BookingResponseDto createBooking(BookingRequestDto request) {
        ApartEntity apartment = apartRepository.findById(request.getApartmentId().intValue())
                .orElseThrow(() -> new RuntimeException("Apartment not found: " + request.getApartmentId()));

        Long nights = ChronoUnit.DAYS.between(request.getCheckIn(), request.getCheckOut());
        if (nights <= 0) {
            throw new RuntimeException("Check out date must be after check in date");
        }

        Long activeBookings = bookingRepository.countActiveBookings(request.getUserId(), LocalDate.now());

        DiscountRequestDto discountRequest = new DiscountRequestDto();
        discountRequest.setCheckIn(request.getCheckIn());
        discountRequest.setCheckOut(request.getCheckOut());
        discountRequest.setUserAge(request.getUserAge());
        discountRequest.setActiveBookingsCount(activeBookings.intValue() + 1);

        List<DiscountDto> discounts = productClient.getDiscounts(discountRequest);

        Integer totalDiscountPercent = Math.min(
                discounts.stream().mapToInt(DiscountDto::getDiscountPercent).sum(),
                80
        );

        Integer pricePerNight = apartment.getPricePerNight();
        Integer baseTotal = pricePerNight * nights.intValue();
        Integer totalPrice = baseTotal - (baseTotal * totalDiscountPercent / 100);

        BookingEntity booking = bookingMapper.toBookingEntity(request, apartment, pricePerNight, totalDiscountPercent, totalPrice);
        BookingEntity newBooking = bookingRepository.save(booking);

        return bookingMapper.toBookingResponseDto(newBooking, apartment);
    }
}
