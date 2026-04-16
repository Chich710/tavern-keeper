package me.rentapp.service;

import me.rentapp.dto.BookingRequestDto;
import me.rentapp.dto.BookingResponseDto;

public interface BookingService {
    BookingResponseDto createBooking(BookingRequestDto request);
}
