package com.example.diploma.service.impl.rentInfoImpl;

import com.example.diploma.model.db.entity.RentInfo;
import com.example.diploma.model.dto.request.RentInfoRequest;
import com.example.diploma.model.dto.response.RentInfoResponse;

import java.util.List;

public interface RentService {

     RentInfo addRent(RentInfoRequest rentInfoRequest);
     RentInfoResponse getRent(Long id);
     List<RentInfoResponse> getAllRent();

    RentInfoResponse getRentForCustomer(String email);

    List<RentInfoResponse> getRentByCar(Long carId);
     void deleteRent(Long id);

   //  RentInfoResponse closeRent(Long id, RentInfoRequest rentInfoRequest);

}
