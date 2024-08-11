package com.example.diploma.service.impl.paymentImpl;

import com.example.diploma.exceptions.CustomException;
import com.example.diploma.model.db.entity.Payment;
import com.example.diploma.model.db.entity.RentInfo;
import com.example.diploma.model.db.repository.PayRepo;
import com.example.diploma.model.db.repository.RentInfoRepo;
import com.example.diploma.model.dto.request.PayRequest;
import com.example.diploma.model.dto.response.PayResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class PayServiceImpl implements PayService {
    private final PayRepo payRepo;
    private final ObjectMapper mapper;
    private final RentInfoRepo rentInfoRepo;

    @Override
    public Payment addPayment(Long rentId, PayRequest request) {
        if (rentInfoRepo.findById(rentId).isEmpty()) {
            throw new CustomException("Сделки не существует", HttpStatus.CONFLICT);
        }
        RentInfo rentInfo = mapper.convertValue(rentInfoRepo.findById(rentId), RentInfo.class);
        boolean compare = (request.getCardDate().isBefore(LocalDate.now()));
        if (request.getNumCard().toString().length() != 16) {
            throw new CustomException("не верно указан номер карты (число символов не соответствует допустимому значению", HttpStatus.CONFLICT);
        }
        if (request.getPasCard().toString().length() != 3) {
            throw new CustomException("CVV код введен не верно", HttpStatus.CONFLICT);
        }
        if (compare) {
            throw new CustomException("срок использования карты истек", HttpStatus.CONFLICT);
        }
        Payment payment = mapper.convertValue(request, Payment.class);
        payment.setRent(rentInfo);
        payment.setCustomer(rentInfo.getCustomer());
        payment.setAmount(rentInfo.getCost());
        payment.setDate(LocalDate.now());
        payment = payRepo.save(payment);
        return payment;
    }
    @Override
    public PayResponse getPayment(Long payId) {
        if (payRepo.findById(payId).isEmpty()) {
            throw new CustomException("оплаты не существует", HttpStatus.CONFLICT);
        }
        return mapper.convertValue(payRepo.findById(payId), PayResponse.class);
    }

}
