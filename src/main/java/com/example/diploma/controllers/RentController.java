package com.example.diploma.controllers;

import com.example.diploma.model.dto.request.RentInfoRequest;
import com.example.diploma.model.dto.response.RentInfoResponse;
import com.example.diploma.service.impl.rentInfoImpl.RentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rent")
@RequiredArgsConstructor
public class RentController {

    private final RentService rentService;

    @PostMapping
    @Operation(summary = "фильтр списка автомобилей")
    public RentInfoResponse rentCar(@RequestBody RentInfoRequest rentInfoRequest) {
        return rentService.rent(rentInfoRequest);
    }
}
