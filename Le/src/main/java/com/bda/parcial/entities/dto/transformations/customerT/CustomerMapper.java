package com.bda.parcial.entities.dto.transformations.customerT;

import com.bda.parcial.entities.Customer;
import com.bda.parcial.entities.dto.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CustomerMapper implements Function<CustomerDto, Customer> {
    @Override
    public Customer apply(CustomerDto customerDto) {
        return new Customer(customerDto.getId(),
                customerDto.getFirstName(),
                customerDto.getLastName(),
                customerDto.getPhone(),
                customerDto.getEmail(),
                customerDto.getSupportRepId());
    }
}
