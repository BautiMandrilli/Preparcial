package ar.edu.utn.frc.backend.controllers;

import ar.edu.utn.frc.backend.models.Customers;
import ar.edu.utn.frc.backend.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customers>> getAllCustomers(){
        List<Customers> customers = customerService.findAll();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Customers>> getCustomerById(@PathVariable Long id) {
        Optional<Customers> customer = customerService.findById(id);
        if(customer.isPresent()){
            return ResponseEntity.ok(customer);
        } else {
            return  ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Customers> createCustomer(@RequestBody Customers customer){
        Customers createdCustomer = customerService.save(customer);
        return ResponseEntity.ok(createdCustomer);
    }

    @PutMapping
    public ResponseEntity<Customers> updateCustomer(@RequestBody Customers customer){
        Customers updatedCustomer = customerService.update(customer);
        if (updatedCustomer != null){
            return ResponseEntity.ok(updatedCustomer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlaylist(@PathVariable Long id){
        boolean deleted = customerService.deleteById(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
