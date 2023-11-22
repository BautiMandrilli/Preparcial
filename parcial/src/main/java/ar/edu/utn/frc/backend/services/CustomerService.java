package ar.edu.utn.frc.backend.services;

import ar.edu.utn.frc.backend.models.Customers;
import ar.edu.utn.frc.backend.repositories.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomersRepository customersRepository;

    public List<Customers> findAll(){
        return customersRepository.findAll();
    }

    public Optional<Customers> findById(Long id) {
        return customersRepository.findById(id);
    }

    public Customers save(Customers customer){
        return customersRepository.save(customer);
    }

    public Customers update(Customers customer){
        Long id = customer.getCustomerId();
        Optional<Customers> existingCustomer = customersRepository.findById(id);
        if (existingCustomer.isPresent()){
            return customersRepository.save(customer);
        } else {
            return null;
        }
    }

    public boolean deleteById(Long id){
        if (customersRepository.existsById(id)) {
            customersRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
