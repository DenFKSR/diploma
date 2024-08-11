package com.example.diploma.controllers;

import com.example.diploma.model.dto.request.CustomerInfoRequest;
import com.example.diploma.model.dto.response.CustomerInfoResponse;
import com.example.diploma.service.impl.customerImpl.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/add")
    @Operation(summary = "Регистрация пользователя")
    public CustomerInfoResponse addCustomer(@RequestBody CustomerInfoRequest request) {
        return customerService.addCustomer(request);
    }

    @GetMapping("/all")
    //  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @Operation(summary = "Получение списка пользователей")
    public List<CustomerInfoResponse> getAllCustomer() {
        return customerService.getAllCustomer();
    }

    @GetMapping("/{id}")// нужен ли этот метод, наверное нужен просто вход авторизация????
    @Operation(summary = "Выбор пользователя")
    public CustomerInfoResponse getCustomer(@PathVariable Long id) {
        return customerService.getCustomer(id);
    }

    @GetMapping("/find_by_email/{email}")
    @Operation(summary = "Выбор пользователя")
    public CustomerInfoResponse getCustomer(@PathVariable String email) {
        return customerService.getCustomer(email);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Редактирование данных пользователя")
    public CustomerInfoResponse updateCustomer(@PathVariable Long id, @RequestBody @Valid CustomerInfoRequest request) {
        return customerService.updateCustomer(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление пользователя")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }
}
