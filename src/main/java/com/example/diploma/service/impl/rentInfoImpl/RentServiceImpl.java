package com.example.diploma.service.impl.rentInfoImpl;

import com.example.diploma.exceptions.EntityNotFoundException;
import com.example.diploma.exceptions.ValidationException;
import com.example.diploma.model.db.entity.Car;
import com.example.diploma.model.db.entity.RentInfo;
import com.example.diploma.model.db.repository.CarRepo;
import com.example.diploma.model.db.repository.CustomerRepo;
import com.example.diploma.model.db.repository.RentInfoRepo;
import com.example.diploma.model.dto.request.RentInfoRequest;
import com.example.diploma.model.dto.response.RentInfoResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@AllArgsConstructor
public class RentServiceImpl implements RentService {

    private final RentInfoRepo rentInfoRepo;
    private final CarRepo carRepo;
    private final CustomerRepo customerRepo;
    @Override
    public RentInfoResponse rent(RentInfoRequest rentInfoRequest) {
        LocalDate startDateRent = rentInfoRequest.getStartDateRent();
        LocalDate endDateRent = rentInfoRequest.getEndDateRent();
        Long customerId = rentInfoRequest.getCustomerId();
        boolean customerAlreadyRented =
                rentInfoRepo.existsRentByCustomerId(
                        customerId, startDateRent, endDateRent
                );
        if (customerAlreadyRented) {
            throw new ValidationException(String.format("Пользователь с id %s уже забронировал машину", customerId));
        }
        Long carId = rentInfoRequest.getCarId();
        boolean carIsAlreadyRented =
                rentInfoRepo.existsRentByCarId(
                        carId, startDateRent, endDateRent
                );
        if (carIsAlreadyRented) {
            throw new ValidationException(String.format("Автомобиль с id %s уже забронирован", carId));
        }
        RentInfo rentInfo = new RentInfo();
        rentInfo.setCustomer(customerRepo.findById(customerId).orElseThrow(() -> new EntityNotFoundException(String.format("Пользователь с id %s не найден", customerId))));
        Car car = carRepo.findById(carId).orElseThrow(() -> new EntityNotFoundException(String.format("Автомобиль с id %s не найден", customerId)));
        rentInfo.setCar(car);
        rentInfo.setStartDateRent(startDateRent);
        rentInfo.setEndDateRent(endDateRent);
        rentInfo.setCost(car.getPrice().multiply(BigDecimal.valueOf(ChronoUnit.DAYS.between(startDateRent, endDateRent))));
        RentInfo rentInfoSaved = rentInfoRepo.save(rentInfo);

        return new RentInfoResponse(rentInfoSaved.getId(), rentInfoSaved.getCar().getId(), rentInfoSaved.getCustomer().getId(), rentInfoSaved.getStartDateRent(),rentInfoSaved.getEndDateRent(), rentInfoSaved.getCost());
    }
}
