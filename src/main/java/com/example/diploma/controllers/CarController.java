package com.example.diploma.controllers;

import com.example.diploma.model.dto.request.CarInfoRequest;
import com.example.diploma.model.dto.response.CarInfoResponse;
import com.example.diploma.service.impl.carImpl.CarService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@Validated
@RequestMapping("/car")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;
    @PostMapping("/add")
    @Operation(summary = "Создание автомобиля")
    public CarInfoResponse addCar(@RequestBody CarInfoRequest request) {
        return carService.addCar(request);
    }

    @GetMapping("/new/{id}")
    @Operation(summary = "Выбор автомобиля для пользователя")
    public List<String> getNewCar(@PathVariable Long id) {
        return carService.getNewCar(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Редактирование данных автомобиля")
    public CarInfoResponse updateCar(@PathVariable Long id, @RequestBody @Valid CarInfoRequest request) {
        return carService.updateCar(id, request);
    }

    @GetMapping("/filter_cars")
    @Operation(summary = "фильтр списка автомобилей")
    public List<CarInfoResponse> getFilterCars
            (@RequestParam(required = false) String brand,
             @RequestParam(required = false) String transmission,
             @RequestParam(required = false) Integer year,
             @RequestParam(required = false) Double price,
             @RequestParam(required = false) String bodyType,
             @RequestParam(required = false) Long id,
             @RequestParam(required = false) String registerNumber) {
        return carService.getFilterCars(brand, transmission, year, price, bodyType, id,registerNumber);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление данных автомобиля")
    public void deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
    }

    @DeleteMapping("/deleteObject/{id}")
    @Operation(summary = "Удаление автомобиля из БД")
    public void deleteCarDB(@PathVariable Long id) {
        carService.deleteCarDB(id);
    }


    @PutMapping("/location/{id}")
    @Operation(summary = "Выбор автомобиля")
    public ArrayList getAddress(@PathVariable Long id) {
        return carService.getAddress(id);
    }


    @GetMapping("/get_image/{id}")
    @Operation(summary = "получить изображение по автомобилю")
    public ResponseEntity<?> getImageByCar(@PathVariable Long id) {
        return carService.getImageByCar(id);
    }
}