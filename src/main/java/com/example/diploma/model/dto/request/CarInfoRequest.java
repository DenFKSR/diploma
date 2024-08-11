package com.example.diploma.model.dto.request;

import com.example.diploma.model.dto.enums.car.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarInfoRequest {

    private String brand;

    private String model;

    private Integer year;

    private Body bodyType;

    private Transmission transmission;

    private EngineType engineType;

    private Integer seatsAmount;

    private BigDecimal price;

    private Double fuelСonsumption;

    private Status status;

    private String address;

    private String registerNumber;

}
