package com.example.diploma.service.impl.customerImpl;

import com.example.diploma.exceptions.CustomException;
import com.example.diploma.model.db.entity.Customer;
import com.example.diploma.model.db.repository.CustomerRepo;
import com.example.diploma.model.dto.enums.customer.CustomerCondition;
import com.example.diploma.model.dto.request.CustomerInfoRequest;
import com.example.diploma.model.dto.response.CustomerInfoResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepo customerRepo;
    private final ObjectMapper mapper;

   // private PasswordEncoder passwordEncoder;



    @Override
    public CustomerInfoResponse addCustomer(CustomerInfoRequest request) {
        String email = request.getEmail();
        if (!EmailValidator.getInstance().isValid(email)){
            throw  new CustomException("Invalid email", HttpStatus.BAD_REQUEST);
        }
        customerRepo.findByEmail(request.getEmail())
                .ifPresent(customer -> {
                    throw new CustomException("Email already exists", HttpStatus.CONFLICT);//исключение:добавление повтор. пользователя
                });
        String numLicense = request.getSerialNumLicense();
        if (numLicense.length()!=10 || !numLicense.matches("\\d{10}")){
            throw  new CustomException("The driver's license number is incorrect. The number must contain only numbers and no more than 10 characters", HttpStatus.BAD_REQUEST);

        }
        Customer customer = mapper.convertValue(request, Customer.class);
        customer.setCustomerCondition(CustomerCondition.CREATED);
        customer.setRoles("USER");
        customer.setCreatedAt(LocalDateTime.now());
        customer.setPassword(customer.getPassword());
        customer = customerRepo.save(customer);
        return mapper.convertValue(customer, CustomerInfoResponse.class);
    }

    @Override
    public CustomerInfoResponse getCustomer(Long id) {
        return mapper.convertValue(customerRepo.findById(id), CustomerInfoResponse.class);
    }

    @Override
    public CustomerInfoResponse getCustomer(String email) {
        return mapper.convertValue(customerRepo.findByEmail(email), CustomerInfoResponse.class);
    }



    @Override
    public CustomerInfoResponse updateCustomer(Long id, CustomerInfoRequest request) {
        Customer customer = mapper.convertValue(getCustomer(id), Customer.class);
        customer.setEmail(request.getEmail()==null ? customer.getEmail() : request.getEmail());
        customer.setPassword(request.getPassword()==null ? customer.getPassword() : request.getPassword());
        customer.setFirstName(request.getFirstName() == null ? customer.getFirstName() : request.getFirstName());
        customer.setLastName(request.getLastName() == null ? customer.getLastName() : request.getLastName());
        customer.setMiddleName(request.getMiddleName() == null ? customer.getMiddleName() : request.getMiddleName());
        customer.setCustomerCondition(CustomerCondition.UPDATED);
        customer.setUpdatedAt(LocalDateTime.now());
        customer.setSerialNumLicense(request.getSerialNumLicense()==null ? customer.getSerialNumLicense() : request.getSerialNumLicense());
        customer = customerRepo.save(customer);
        return mapper.convertValue(customer, CustomerInfoResponse.class);

    }



    @Override
    public void deleteCustomer(Long id) {
        Customer customer = mapper.convertValue(getCustomer(id), Customer.class);
        customer.setCustomerCondition(CustomerCondition.DELETED);
        customer.setUpdatedAt(LocalDateTime.now());
        customerRepo.save(customer);
    }
    @Override
    public List<CustomerInfoResponse> getAllCustomer(){
        return customerRepo.findAll()
                .stream()
                .map(customer->mapper.convertValue(customer, CustomerInfoResponse.class))
                .collect(Collectors.toList());
    }
}
