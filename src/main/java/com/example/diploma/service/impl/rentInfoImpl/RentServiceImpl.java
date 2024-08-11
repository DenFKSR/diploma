package com.example.diploma.service.impl.rentInfoImpl;

import com.example.diploma.exceptions.CustomException;
import com.example.diploma.exceptions.ValidationException;
import com.example.diploma.model.db.entity.Car;
import com.example.diploma.model.db.entity.Customer;
import com.example.diploma.model.db.entity.RentInfo;
import com.example.diploma.model.db.repository.CarRepo;
import com.example.diploma.model.db.repository.CustomerRepo;
import com.example.diploma.model.db.repository.RentInfoRepo;
import com.example.diploma.model.dto.enums.car.Condition;
import com.example.diploma.model.dto.enums.rent.RentStatus;
import com.example.diploma.model.dto.request.RentInfoRequest;
import com.example.diploma.model.dto.response.RentInfoResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RentServiceImpl implements RentService {

    private final RentInfoRepo rentInfoRepo;
    private final CarRepo carRepo;
    private final CustomerRepo customerRepo;
    private final ObjectMapper mapper;

    public static final String ERR_MSG = "deal rent not found";

    @Override
    public RentInfo addRent(RentInfoRequest rentInfoRequest) {
        RentInfo rentInfo = mapper.convertValue(rentInfoRequest, RentInfo.class);
        Long check = (rentInfoRequest.getCarId());
        carRepo.findById(check);
        Car car = mapper.convertValue(carRepo.findById(check), Car.class);
        List<RentInfoResponse> rentInfo1 = getRentByCar(rentInfoRequest.getCarId());
        for (RentInfoResponse rentInfo2 : rentInfo1) {
            boolean compare1 = (rentInfo.getStartDateRent().isBefore(rentInfo2.getStartDateRent())) && (rentInfo2.getEndDateRent().isBefore(rentInfo.getEndDateRent()));
            boolean compare2 = rentInfo2.getStartDateRent().isBefore(rentInfo.getStartDateRent()) && rentInfo2.getEndDateRent().isBefore(rentInfo.getEndDateRent()) &&   rentInfo.getStartDateRent().isBefore(rentInfo2.getEndDateRent());
            boolean compare3 =  rentInfo2.getStartDateRent().isBefore(rentInfo.getStartDateRent()) &&  rentInfo.getEndDateRent().isBefore(rentInfo2.getEndDateRent());
            boolean compare4 = (rentInfo.getStartDateRent().isEqual(rentInfo2.getStartDateRent())) && (rentInfo2.getEndDateRent().isEqual(rentInfo.getEndDateRent()));
            if (compare1 || compare2 || compare3 || compare4) {
                throw new ValidationException(String.format("Машина на эти даты забронирована"));
            }
        }
        Customer customer = mapper
                .convertValue(customerRepo.findById(rentInfoRequest.getCustomerId()), Customer.class);
       if (customer.getStatus() == com.example.diploma.model.dto.enums.customer.Status.RENTER) {
            throw new ValidationException(String.format("Пользователь с id %s уже забронировал машину", customer.getId()));
        }
        customer.setStatus(com.example.diploma.model.dto.enums.customer.Status.valueOf("RENTER"));
        rentInfo.setEmail(customer.getEmail());
        rentInfo.setCar(car);
        rentInfo.setCustomer(customer);
        rentInfo.setCondition(Condition.CREATED);
        rentInfo.setRentStatus(RentStatus.OPEN);//КОГДА ЗАДАВАТЬ СТАТУС
        rentInfo.setCreatedAt(LocalDateTime.now());
        rentInfo.setCost(car.getPrice()
                .multiply(BigDecimal.valueOf(ChronoUnit.DAYS.between(rentInfoRequest.getStartDateRent(), rentInfoRequest.getEndDateRent()))));
        rentInfo = rentInfoRepo.save(rentInfo);
        return rentInfo;
    }

    @Override
    public RentInfoResponse getRent(Long id) {
        return  mapper.convertValue(rentInfoRepo.findById(id), RentInfoResponse.class);
    }

    @Override
    public RentInfoResponse getRentForCustomer(String email) {
        return mapper.convertValue(rentInfoRepo.findByEmail(email), RentInfoResponse.class);
    }

    @Override
    public List<RentInfoResponse> getRentByCar(Long carId) {
        return rentInfoRepo.findByCarId(carId).stream()
                .map(rent -> mapper.convertValue(rent, RentInfoResponse.class))
                .collect(Collectors.toList());

    }
    @Override
    public List<RentInfoResponse> getAllRent() {
        return rentInfoRepo.findAll()
                .stream()
                .map(rent -> mapper.convertValue(rent, RentInfoResponse.class))
                .collect(Collectors.toList());

    }



    @Override
    public void deleteRent(Long id) {
        RentInfo rent = mapper.convertValue(getRent(id), RentInfo.class);
        rentInfoRepo.findById(id).orElseThrow(() -> new CustomException(ERR_MSG, HttpStatus.NOT_FOUND));
        rent.setCondition(Condition.DELETED);
        rent.setUpdatedAt(LocalDateTime.now());
        rentInfoRepo.save(rent);
    }





//    @Override
//    public  RentInfoResponse closeRent(Long id, RentInfoRequest request){
//        if (rentInfoRepo.findById(id).isEmpty()){
//            throw new CustomException("такой сделки не существует", HttpStatus.CONFLICT);
//        }
//        RentInfo rent = mapper.convertValue(getRent(id), RentInfo.class);
//        rent.setRentStatus(RentStatus.CLOSED);
//        Car car = mapper.convertValue(carRepo.findById(rent.getCarId()), Car.class);
//        car.setStatus(Status.FREE);
//        Customer customer = mapper
//                .convertValue(customerRepo.findById(rent.getCustomerId()), Customer.class);
//        customer.setStatus(com.example.diploma.model.dto.enums.customer.Status.FREE);
//        rent.setFeedback(request.toString());
//        return  mapper.convertValue(rent, RentInfoResponse.class);
//    }



}
