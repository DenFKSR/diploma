package com.example.diploma.controllers;

import com.example.diploma.model.db.entity.Payment;
import com.example.diploma.model.dto.request.PayRequest;
import com.example.diploma.model.dto.response.PayResponse;
import com.example.diploma.service.impl.paymentImpl.PayService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/pay")
@RequiredArgsConstructor
public class PayController {
    private final PayService payService;

    @PostMapping("/add/{rentId}")
    @Operation(summary = "создание операции оплаты")
    public  Payment addPayment (@PathVariable Long rentId, @RequestBody PayRequest request){
        return payService.addPayment(rentId, request);
    }
    @GetMapping("/{payId}")
    @Operation(summary = "информация об оплате")
    public PayResponse getPayment(@PathVariable Long payId) {
        return payService.getPayment(payId);
    }
}
