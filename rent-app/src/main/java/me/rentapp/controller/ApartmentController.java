package me.rentapp;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apart")
@RequiredArgsConstructor
public class ApartmentController {
    private final ApartmentService apartmentService;

    @PostMapping("/create")
    public ApartmentEntity create(@RequestBody ApartmentCreateRequestDto payload) {
        return apartmentService.create(payload);
    }
}