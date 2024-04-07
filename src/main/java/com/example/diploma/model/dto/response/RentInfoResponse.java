package com.example.diploma.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class RentInfoResponse {
    private Long id;

    private Long carId;

    private Long customerId;

    private LocalDate startDateRent;

    private LocalDate endDateRent;

    private BigDecimal cost;

}
