package me.rentapp.controller;

import lombok.RequiredArgsConstructor;
import me.rentapp.dto.ApartCreateRequestDto;
import me.rentapp.dto.ApartResponseDto;
import me.rentapp.service.ApartService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/apart")
@RequiredArgsConstructor
public class ApartController {
    private final ApartService apartService;

    @PostMapping("/create")
    public ApartResponseDto create(@RequestBody ApartCreateRequestDto payload) {
        return apartService.create(payload);
    }

    @GetMapping("/list")
    public List<ApartResponseDto> getList() {
        return apartService.getList();
    }
}
