package utn.frc.parcial1.parcialback.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.frc.parcial1.parcialback.entities.dto.CustomersDto;
import utn.frc.parcial1.parcialback.services.customersService.CustomerService;

import java.util.List;
@RestController
@RequestMapping("api/customers")
public class CustomerController {
    private  final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomersDto> getById(@PathVariable("id") Long id) {
        CustomersDto res = customerService.getById(id);
        return ResponseEntity.ok(res);
    }
    @GetMapping
    public ResponseEntity<List<CustomersDto>> getAll(){
        List<CustomersDto> values = customerService.getAll();
        return ResponseEntity.ok(values);
    }

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody CustomersDto entity) {
        customerService.add(entity);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PostMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody CustomersDto entity,
                                       @PathVariable("id") Long id){
        customerService.update(entity, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id")Long id) {
        customerService.delete(id);
        return ResponseEntity.ok().build();
    }
}
