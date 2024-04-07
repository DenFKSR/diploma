package com.example.diploma.service.impl.rentInfoImpl;

import com.example.diploma.model.dto.request.RentInfoRequest;
import com.example.diploma.model.dto.response.RentInfoResponse;

public interface RentService {

     RentInfoResponse rent(RentInfoRequest rentInfoRequest);
}
