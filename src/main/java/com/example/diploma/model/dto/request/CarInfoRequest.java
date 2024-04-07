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

 // @NotBlank(message = "Должен быть указан брэнд автомобиля")
    private String brand;

  // @NotBlank
    private String model;

   // @NotNull
    private Integer year;

   //@NotNull
    private Body bodyType;

  // @NotNull
    private Transmission transmission;

 // @NotNull
    private EngineType engineType;

 // @NotNull
    private Integer seatsAmount;

 // @NotNull
    private BigDecimal price;

    // @NotNull
    private Double fuelСonsumption;

 // @NotNull
    private Status status;

    private Condition statusCondition;

  // @NotBlank
    private String address;

  // @NotBlank
    private String registerNumber;
}
