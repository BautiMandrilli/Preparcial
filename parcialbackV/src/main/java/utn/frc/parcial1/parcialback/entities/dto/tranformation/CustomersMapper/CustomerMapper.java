package utn.frc.parcial1.parcialback.entities.dto.tranformation.CustomersMapper;

import utn.frc.parcial1.parcialback.entities.Customers;
import utn.frc.parcial1.parcialback.entities.dto.CustomersDto;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class CustomerMapper implements Function<CustomersDto, Customers> {
    @Override
    public Customers apply(CustomersDto customersDto) {
        return new Customers(customersDto.getCustomersId(), customersDto.getFirstName(),
                customersDto.getLastName(), customersDto.getCompany(),
                customersDto.getAddress(), customersDto.getEmail()
        );
    }
}
