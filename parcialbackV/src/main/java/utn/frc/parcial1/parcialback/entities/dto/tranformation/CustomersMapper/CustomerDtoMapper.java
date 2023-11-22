package utn.frc.parcial1.parcialback.entities.dto.tranformation.CustomersMapper;

import org.springframework.stereotype.Service;
import utn.frc.parcial1.parcialback.entities.Customers;
import utn.frc.parcial1.parcialback.entities.dto.CustomersDto;

import java.util.function.Function;
@Service
public class CustomerDtoMapper implements Function<Customers, CustomersDto> {
    @Override
    public CustomersDto apply(Customers customers){
        return new CustomersDto(
          customers.getCustomerId(),customers.getFirstName(), customers.getLastName(), customers.getCompany(),
                customers.getAddress(), customers.getEmail()
        );
    }
}
