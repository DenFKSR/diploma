package com.example.diploma.model.dto.response;

import com.example.diploma.model.dto.enums.car.EngineType;
import com.example.diploma.model.dto.enums.car.Transmission;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Data
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CarInfoResponseSh {
    String brand;
    String model;
    Integer year;
    EngineType engineType;
    Transmission transmission;
}
