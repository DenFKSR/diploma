package com.example.diploma.service.impl.paymentImpl;

import com.example.diploma.model.db.entity.Payment;
import com.example.diploma.model.dto.request.PayRequest;
import com.example.diploma.model.dto.response.PayResponse;

public interface PayService {
    Payment addPayment (Long rentId, PayRequest request);
    PayResponse getPayment (Long payId);
}
