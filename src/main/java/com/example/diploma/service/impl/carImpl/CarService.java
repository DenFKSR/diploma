package com.example.diploma.service.impl.carImpl;

import com.example.diploma.model.dto.request.CarInfoRequest;
import com.example.diploma.model.dto.response.CarInfoResponse;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public interface CarService {


    CarInfoResponse addCar(CarInfoRequest request);

    CarInfoResponse updateCar (Long id, CarInfoRequest request);

    void deleteCar(Long id);

    List<CarInfoResponse> getFilterCars(String brand, String transmission, Integer year, Double price, String bodyType,  Long id,String registerNumber);
//    CarInfoResponse selectCar(Long carId, Long customerId);

    ArrayList getAddress(Long id);

    List<String> getNewCar(Long id);

     void deleteCarDB(Long id);

    ResponseEntity<?> getImageByCar(Long id);
}
