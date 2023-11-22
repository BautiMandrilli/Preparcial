package com.bda.parcial.controllers;


import com.bda.parcial.entities.Customer;
import com.bda.parcial.entities.dto.CustomerDto;
import com.bda.parcial.services.customer.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAll() {
        List<CustomerDto> values = customerService.getAll();
        return ResponseEntity.ok(values);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getById(@PathVariable ("id") Long id) {
        CustomerDto customer = customerService.getById(id);
        return ResponseEntity.ok(customer);

    }
    @PostMapping
    public ResponseEntity<CustomerDto> add(@RequestBody CustomerDto entity) {
        customerService.add(entity);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody CustomerDto entity, @PathVariable ("id") Long id) {
        customerService.update(entity, id);
        return ResponseEntity.ok().build();

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable ("id") Long id) {
        CustomerDto customer = customerService.delete(id);
        return ResponseEntity.ok().build();
    }



}
