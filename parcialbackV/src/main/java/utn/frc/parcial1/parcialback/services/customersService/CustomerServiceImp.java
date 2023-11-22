package utn.frc.parcial1.parcialback.services.customersService;



import org.springframework.stereotype.Service;
import utn.frc.parcial1.parcialback.entities.Customers;
import utn.frc.parcial1.parcialback.entities.dto.CustomersDto;
import utn.frc.parcial1.parcialback.entities.dto.tranformation.CustomersMapper.CustomerDtoMapper;
import utn.frc.parcial1.parcialback.entities.dto.tranformation.CustomersMapper.CustomerMapper;
import utn.frc.parcial1.parcialback.repositories.CustomersRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
@Service
public class CustomerServiceImp implements CustomerService {
    private final CustomersRepository customersRepository;
    private final CustomerDtoMapper customerDtoMapper;
    private final CustomerMapper customerMapper;

    public CustomerServiceImp(CustomersRepository customersRepository,
                              CustomerDtoMapper customerDtoMapper,
                              CustomerMapper customerMapper) {
        this.customersRepository = customersRepository;
        this.customerDtoMapper = customerDtoMapper;
        this.customerMapper = customerMapper;
    }

    @Override
    public void add(CustomersDto entity) {
        Customers customers = Optional.of(entity).map(customerMapper).get();
        customersRepository.save(customers);
    }

    @Override
    public CustomersDto getById(Long id){
        Optional<Customers> customers = customersRepository.findById(id);
        return customers
                .map(customerDtoMapper)
                .orElseThrow();
    }
    @Override
    public List<CustomersDto> getAll() {
        List<Customers> values = customersRepository.findAll();
        return values
                .stream()
                .map(customerDtoMapper)
                .toList();
    }
    @Override
    public CustomersDto delete(Long id){
        Optional<Customers> optionalCustomers = customersRepository
                .findById(id);
        optionalCustomers.ifPresent(customersRepository::delete);
        return optionalCustomers
                .map(customerDtoMapper)
                .orElseThrow();
    }

    @Override
    public void update(CustomersDto entity, Long id) {
        Optional<Customers> customers = customersRepository.findById(id);
        Customers customerExistente = customers.get();
        customerExistente.setFirstName(entity.getFirstName());
        customerExistente.setLastName(entity.getLastName());
        customerExistente.setCompany(entity.getCompany());
        customerExistente.setAddress(entity.getAddress());
        customerExistente.setEmail(entity.getEmail());
        customersRepository.save(customerExistente);
    }

}
