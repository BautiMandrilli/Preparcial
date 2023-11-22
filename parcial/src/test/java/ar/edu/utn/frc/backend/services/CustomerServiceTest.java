package ar.edu.utn.frc.backend.services;

import ar.edu.utn.frc.backend.models.Customers;
import ar.edu.utn.frc.backend.repositories.CustomersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomersRepository customersRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAll() {
        List<Customers> customers = new ArrayList<>();
        customers.add(new Customers("John", "Doe", "Company 1", "Address 1", "City 1", "State 1", "Country 1", "12345", "123-456-7890", "123-456-7890", "john@example.com", 1L, 1L));
        customers.add(new Customers("Jane", "Smith", "Company 2", "Address 2", "City 2", "State 2", "Country 2", "54321", "987-654-3210", "987-654-3210", "jane@example.com", 2L, 2L));

        Mockito.when(customersRepository.findAll()).thenReturn(customers);

        List<Customers> result = customerService.findAll();

        assertEquals(2, result.size());
    }

    @Test
    public void testFindById() {
        Long customerId = 1L;
        Customers customer = new Customers("John", "Doe", "Company 1", "Address 1", "City 1", "State 1", "Country 1", "12345", "123-456-7890", "123-456-7890", "john@example.com", 1L, customerId);

        Mockito.when(customersRepository.findById(customerId)).thenReturn(Optional.of(customer));

        Optional<Customers> result = customerService.findById(customerId);

        assertTrue(result.isPresent());
        assertEquals(customer, result.get());
    }

    @Test
    public void testFindByIdNotFound() {
        Long customerId = 1L;

        Mockito.when(customersRepository.findById(customerId)).thenReturn(Optional.empty());

        Optional<Customers> result = customerService.findById(customerId);

        assertFalse(result.isPresent());
    }

    @Test
    public void testSave() {
        Customers customer = new Customers("New", "Customer", "New Company", "New Address", "New City", "New State", "New Country", "54321", "987-654-3210", "987-654-3210", "new@example.com", 2L, 1L);

        Mockito.when(customersRepository.save(customer)).thenReturn(customer);

        Customers savedCustomer = customerService.save(customer);

        assertEquals(customer, savedCustomer);
    }

    @Test
    public void testUpdate() {
        Long customerId = 1L;
        Customers existingCustomer = new Customers("John", "Doe", "Company 1", "Address 1", "City 1", "State 1", "Country 1", "12345", "123-456-7890", "123-456-7890", "john@example.com", 1L, customerId);
        Customers updatedCustomer = new Customers("Updated", "Customer", "Updated Company", "Updated Address", "Updated City", "Updated State", "Updated Country", "54321", "987-654-3210", "987-654-3210", "updated@example.com", 2L, customerId);

        Mockito.when(customersRepository.findById(customerId)).thenReturn(Optional.of(existingCustomer));
        Mockito.when(customersRepository.save(updatedCustomer)).thenReturn(updatedCustomer);

        Customers result = customerService.update(updatedCustomer);

        assertEquals(updatedCustomer, result);
    }

    @Test
    public void testUpdateNotFound() {
        Long customerId = 1L;
        Customers updatedCustomer = new Customers("Updated", "Customer", "Updated Company", "Updated Address", "Updated City", "Updated State", "Updated Country", "54321", "987-654-3210", "987-654-3210", "updated@example.com", 2L, customerId);

        Mockito.when(customersRepository.findById(customerId)).thenReturn(Optional.empty());

        Customers result = customerService.update(updatedCustomer);

        assertNull(result);
    }

    @Test
    public void testDeleteById() {
        Long customerId = 1L;

        Mockito.when(customersRepository.existsById(customerId)).thenReturn(true);

        boolean deleted = customerService.deleteById(customerId);

        assertTrue(deleted);
    }

    @Test
    public void testDeleteByIdNotFound() {
        Long customerId = 1L;

        Mockito.when(customersRepository.existsById(customerId)).thenReturn(false);

        boolean deleted = customerService.deleteById(customerId);

        assertFalse(deleted);
    }
}

