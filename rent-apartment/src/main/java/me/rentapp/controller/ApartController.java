package me.rentapp.controller;

import lombok.RequiredArgsConstructor;
import me.rentapp.dto.ApartCreateRequestDto;
import me.rentapp.dto.ApartResponseDto;
import me.rentapp.dto.BookingRequestDto;
import me.rentapp.dto.BookingResponseDto;
import me.rentapp.service.ApartService;
import me.rentapp.service.BookingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/aparts")
@RequiredArgsConstructor
public class ApartController {
    private final ApartService apartService;
    private final BookingService bookingService;

    @PostMapping("/create")
    public ApartResponseDto create(@RequestBody ApartCreateRequestDto payload) {
        return apartService.create(payload);
    }

    @GetMapping("/list")
    public List<ApartResponseDto> getList() {
        return apartService.getList();
    }

    @PostMapping("/book")
    public BookingResponseDto createBooking(
            @RequestBody BookingRequestDto request
    ) {
        return bookingService.createBooking(request);
    }
}
