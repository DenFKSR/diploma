package com.example.diploma.model.dto.response;

import com.example.diploma.model.dto.enums.car.Condition;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
//@JsonIgnoreProperties(ignoreUnknown = true)
public class RentInfoResponse {
    Long id;
    LocalDate startDateRent;
    LocalDate endDateRent;
    BigDecimal cost;
    String email;
    Condition condition;
    CarInfoResponseSh car;
    CustomerInfoResponseSh customer;
}
