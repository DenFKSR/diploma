package com.example.diploma.controllers;

import com.example.diploma.model.db.entity.RentInfo;
import com.example.diploma.model.dto.request.RentInfoRequest;
import com.example.diploma.model.dto.response.RentInfoResponse;
import com.example.diploma.service.impl.rentInfoImpl.RentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rent")
@RequiredArgsConstructor
public class RentController {

    private final RentService rentService;

    @PostMapping("/add")
    @Operation(summary = "создание сделки")
    public RentInfo addRent(@RequestBody RentInfoRequest rentInfoRequest) {
        return rentService.addRent(rentInfoRequest);
    }
    @GetMapping("/{id}")
    @Operation(summary = "Выбор сделки")
    public  RentInfoResponse getRent(@PathVariable Long id) {
        return rentService.getRent(id);
    }
    @GetMapping("/find_by_email/{email}")
    RentInfoResponse getRentForCustomer(@PathVariable String email){return rentService.getRentForCustomer(email);}
    @GetMapping("/all")
    @Operation(summary = "Получение списка сделок")
    public List<RentInfoResponse> getAllRent() {
        return rentService.getAllRent();
    }


    @GetMapping("/findRentsByCar/{carId}")
    @Operation(summary = "Поиск сделок по машине")
    public List<RentInfoResponse> getRentByCar(@PathVariable Long carId)
    {
        return rentService.getRentByCar(carId);
    }


//    @PutMapping("/close/{id}")
//    @Operation(summary = "Поиск сделок по машине")
//    public RentInfoResponse closeRent(@PathVariable Long id, @RequestBody RentInfoRequest request)
//    {
//        return rentService.closeRent(id, request);
//    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление данных сделки")
    public void deleteRent(@PathVariable Long id) {
        rentService.deleteRent(id);
    }
}
