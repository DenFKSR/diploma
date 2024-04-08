package com.example.diploma.model.dto.response;

import com.example.diploma.model.dto.request.CarInfoRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Setter;

@Data
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CarInfoResponse extends CarInfoRequest {
    Long id;
    CustomerInfoResponse customer;
}
