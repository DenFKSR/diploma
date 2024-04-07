package com.example.diploma.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerInfoRequest {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String middleName;
    private Integer age;
    private String serialNumLicense;
}
