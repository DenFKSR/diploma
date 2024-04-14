package com.example.diploma.service.impl.carImpl;

import com.example.diploma.exceptions.CustomException;
import com.example.diploma.model.db.entity.Car;
import com.example.diploma.model.db.entity.Customer;
import com.example.diploma.model.db.repository.CarRepo;
import com.example.diploma.model.db.repository.CustomerRepo;
import com.example.diploma.model.dto.enums.car.Condition;
import com.example.diploma.model.dto.enums.car.Status;
import com.example.diploma.model.dto.request.CarInfoRequest;
import com.example.diploma.model.dto.response.CarInfoResponse;
import com.example.diploma.model.dto.response.CustomerInfoResponse;
import com.example.diploma.service.impl.customerImpl.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CarServiceImpl implements CarService {
    private final CarRepo carRepo;
    private final ObjectMapper mapper;
private final CustomerRepo customerRepo;
private final CustomerService customerService;
    public static final String ERR_MSG = "car not found";

    @Override
    public CarInfoResponse addCar(CarInfoRequest request) {
        carRepo.findByRegisterNumber(request.getRegisterNumber())
                .ifPresent(car -> {
                    throw new CustomException("Car already exists", HttpStatus.CONFLICT);
                });
        Car car = mapper.convertValue(request, Car.class);
        car.setCondition(Condition.CREATED);
        car.setStatus(Status.FREE);
        car.setCreatedAt(LocalDateTime.now());
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
    public CarInfoResponse selectCar(Long carId, Long customerId) {
        Car car = mapper.convertValue(getCar(carId), Car.class);
        carRepo.findById(carId).orElseThrow(() -> new CustomException(ERR_MSG, HttpStatus.NOT_FOUND));
        Customer customer = mapper.convertValue(customerService.getCustomer(customerId), Customer.class) ;
        customerRepo.findById(customerId).orElseThrow(() -> new CustomException("User not found", HttpStatus.NOT_FOUND));
        customer.setCar(List.of(car));
        car.setCustomer(customer);
        car.setStatus(Status.ORDERED);
        car = carRepo.save(car);
        CustomerInfoResponse customerInfoResponse = mapper.convertValue(customer, CustomerInfoResponse.class);
        CarInfoResponse carInfoResponse = mapper.convertValue(car, CarInfoResponse.class);
        carInfoResponse.setCustomer(customerInfoResponse);
        return carInfoResponse;
    }
    @Override
    public ArrayList  getAddress(Long id) {
        Car car = mapper.convertValue(getCar(id), Car.class);
        double number = Math.random() * 100;
        int num2 = (int) number;
        double memArray = Math.random() * 10;
        int memArray2 = (int) memArray;
        ArrayList<String> lottery = new ArrayList<>();
        lottery.add("Адмиралтейская наб, дом " + num2);
        lottery.add("Бойцова пер., дом " + num2);
        lottery.add("Бумажная ул., дом " + num2);
        lottery.add("Введенский канал, дом " + num2);
        lottery.add("Володи Ермака ул., дом " + num2);
        lottery.add("Гороховая ул., дом " + num2);
        lottery.add("Дворцовый проезд, дом " + num2);
        lottery.add("Дерптский пер., дом " + num2);
        lottery.add("Дровяной пер., дом " + num2);
        lottery.add("Измайловский просп., дом " + num2);
        lottery.add("Малый Казачий пер., дом " + num2);
        lottery.add("Константина Заслонова ул., дом " + num2);
        Collections.shuffle(lottery);
        car.setAddress(lottery.get(memArray2));
        carRepo.save(car);
        ArrayList<String> info = new ArrayList<>();
        info.add(car.getBrand());
        info.add(car.getModel());
        info.add(car.getRegisterNumber());
        info.add(car.getAddress());
        return info;
    }






}

