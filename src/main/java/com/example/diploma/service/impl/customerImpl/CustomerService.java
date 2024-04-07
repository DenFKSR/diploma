package com.example.diploma.service.impl.customerImpl;

import com.example.diploma.model.dto.request.CustomerInfoRequest;
import com.example.diploma.model.dto.response.CustomerInfoResponse;

import java.util.List;

public interface CustomerService {
    CustomerInfoResponse addCustomer(CustomerInfoRequest request);
    CustomerInfoResponse getCustomer(Long id);
    CustomerInfoResponse updateCustomer (Long id, CustomerInfoRequest request);
    CustomerInfoResponse setStatus(Long id);
    CustomerInfoResponse setDeal(Long id);
    List<CustomerInfoResponse> getAllCustomer();


    void deleteCustomer(Long id);
}
