package com.example.diploma.service.impl.carImpl;

import com.example.diploma.exceptions.CustomException;
import com.example.diploma.model.db.entity.Car;
import com.example.diploma.model.db.entity.Customer;
import com.example.diploma.model.db.repository.CarRepo;
import com.example.diploma.model.dto.enums.car.Condition;
import com.example.diploma.model.dto.request.CarInfoRequest;
import com.example.diploma.model.dto.response.CarInfoResponse;
import com.example.diploma.service.impl.customerImpl.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CarServiceImpl implements CarService {
    private final CarRepo carRepo;
    private final ObjectMapper mapper;
    private final CustomerService customerService;
    public static final String ERR_MSG = "car not found";

    @Override
    public CarInfoResponse addCar(CarInfoRequest request) {
        Car car = mapper.convertValue(request, Car.class);
        car = carRepo.save(car);
        return mapper.convertValue(car, CarInfoResponse.class);
    }

    @Override
    public CarInfoResponse getCar(Long id) {
        return mapper.convertValue(carRepo.findById(id), CarInfoResponse.class);
    }

    @Override
    public CarInfoResponse updateCar(Long id, CarInfoRequest request) {
        Car car = mapper.convertValue(getCar(id), Car.class);
        car.setBrand(request.getBrand() == null ? car.getBrand() : request.getBrand());
        car.setModel(request.getModel() == null ? car.getModel() : request.getModel());
        car.setYear(request.getYear() == null ? car.getYear() : request.getYear());
        car.setBodyType(request.getBodyType() == null ? car.getBodyType() : request.getBodyType());
        car.setTransmission(request.getTransmission() == null ? car.getTransmission() : request.getTransmission());
        car.setEngineType(request.getEngineType() == null ? car.getEngineType() : request.getEngineType());
        car.setSeatsAmount(request.getSeatsAmount() == null ? car.getSeatsAmount() : request.getSeatsAmount());
        car.setPrice(request.getPrice() == null ? car.getPrice() : request.getPrice());
        car.setFuelСonsumption(request.getFuelСonsumption() == null ? car.getFuelСonsumption() : request.getFuelСonsumption());
        car.setAddress(request.getAddress() == null ? car.getAddress() : request.getAddress());
        car.setStatus(request.getStatus() == null ? car.getStatus() : request.getStatus());
        car.setRegisterNumber(request.getRegisterNumber() == null ? car.getRegisterNumber() : request.getRegisterNumber());
        car.setCondition(Condition.UPDATED);
        car.setUpdatedAt(LocalDateTime.now());
        car = carRepo.save(car);
        return mapper.convertValue(car, CarInfoResponse.class);
    }

    @Override
    public void deleteCar(Long id) {
        Car car = mapper.convertValue(getCar(id), Car.class);
        carRepo.findById(id).orElseThrow(() -> new CustomException(ERR_MSG, HttpStatus.NOT_FOUND));
        car.setCondition(Condition.DELETED);
        car.setUpdatedAt(LocalDateTime.now());
        carRepo.save(car);
    }

    @Override
    public List<CarInfoResponse> getAllCars() {
        return carRepo.findAll()
                .stream()
                .map(car -> mapper.convertValue(car, CarInfoResponse.class))
                .collect(Collectors.toList());

    }

    @Override
    public List<CarInfoResponse> getFilterCars(String brand, String transmission, Integer year, Double price, String bodyType) {
        return carRepo.findByFilters(brand, transmission, year, price, bodyType)
                .stream()
                .map(car -> mapper.convertValue(car, CarInfoResponse.class))
                .collect(Collectors.toList());
    }


    @Override
    public CarInfoResponse selectCar(Long id, Customer customer) {
        Car car = mapper.convertValue(getCar(id), Car.class);
        carRepo.findById(id).orElseThrow(() -> new CustomException(ERR_MSG, HttpStatus.NOT_FOUND));
        car.setCustomer(customer);
        car = carRepo.save(car);
        return mapper.convertValue(car, CarInfoResponse.class);

    }
}