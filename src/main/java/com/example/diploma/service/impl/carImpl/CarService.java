package com.example.diploma.service.impl.carImpl;

import com.example.diploma.model.dto.request.CarInfoRequest;
import com.example.diploma.model.dto.response.CarInfoResponse;

import java.util.ArrayList;
import java.util.List;

public interface CarService {
    CarInfoResponse addCar(CarInfoRequest request);
    CarInfoResponse getCar(Long id);
    CarInfoResponse updateCar (Long id, CarInfoRequest request);
    void deleteCar(Long id);
    List<CarInfoResponse> getAllCars();

    List<CarInfoResponse> getFilterCars(String brand, String transmission, Integer year, Double price, String bodyType);
    CarInfoResponse selectCar(Long carId, Long customerId);

    ArrayList getAddress(Long id);
}
