package com.example.diploma.model.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PayResponseSh {
    String firstNameCustomer;
    String lastNameCustomer;
    BigDecimal costOfRent;
    LocalDate dateOfPay;
}
