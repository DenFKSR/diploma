package com.example.diploma.service.impl.rentInfoImpl;

import com.example.diploma.model.dto.request.RentInfoRequest;
import com.example.diploma.model.dto.response.RentInfoResponse;

import java.util.List;

public interface RentService {

     RentInfoResponse addRent(RentInfoRequest rentInfoRequest);
     RentInfoResponse getRent(Long id);
     List<RentInfoResponse> getAllRent();
     List<RentInfoResponse> getRentByCar(Long carId);
     void deleteRent(Long id);

     RentInfoResponse closeRent(Long id, RentInfoRequest rentInfoRequest);

}
