package com.bda.parcial.entities.dto.transformations.customerT;

import com.bda.parcial.entities.Customer;
import com.bda.parcial.entities.dto.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CustomerDtoMapper implements Function<Customer, CustomerDto> {

    @Override
    public CustomerDto apply(Customer customer) {
        return new CustomerDto(customer.getId(), customer.getFirstName(), customer.getLastName(),
        customer.getPhone(), customer.getEmail(), customer.getSupportRepId());
    }
}
