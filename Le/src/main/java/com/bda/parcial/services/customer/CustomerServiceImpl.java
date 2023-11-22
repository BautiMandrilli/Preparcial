package com.bda.parcial.services.customer;

import com.bda.parcial.entities.Customer;
import com.bda.parcial.entities.dto.CustomerDto;
import com.bda.parcial.entities.dto.transformations.customerT.CustomerDtoMapper;
import com.bda.parcial.entities.dto.transformations.customerT.CustomerMapper;
import com.bda.parcial.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;
    private final CustomerDtoMapper customerDtoMapper;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerDtoMapper customerDtoMapper, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerDtoMapper = customerDtoMapper;
        this.customerMapper = customerMapper;
    }

    @Override
    public void add(CustomerDto entity) {
        Customer customerNuevo = customerMapper.apply(entity);
        customerRepository.save(customerNuevo);

    }

    @Override
    public CustomerDto getById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.map(customerDtoMapper).orElseThrow(()
                -> new RuntimeException("El cliente no se encontró con el ID proporcionado")) ;

    }

    @Override
    public List<CustomerDto> getAll() {
        List<Customer> values = customerRepository.findAll();
        return values.stream().map(customerDtoMapper).toList();
    }

    @Override
    public CustomerDto delete(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        customer.ifPresent(customerRepository::delete);
        return customer.map(customerDtoMapper).orElseThrow();
    }

    @Override
    public void update(CustomerDto entity, Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        Customer customerExistente = customer.get();

        customerExistente.setFirstName(entity.getFirstName());
        customerExistente.setLastName(entity.getLastName());
        customerExistente.setEmail(entity.getEmail());
        customerExistente.setPhone(entity.getPhone());
        customerExistente.setSupportRepId(entity.getSupportRepId());

        customerRepository.save(customerExistente);
    }
}
