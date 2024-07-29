package com.example.diploma.model.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentInfoRequest {
    private Long carId;
    private Long customerId;


    private LocalDate startDateRent;
    private LocalDate endDateRent;
    //private String email;
   // private String feedback;

}
