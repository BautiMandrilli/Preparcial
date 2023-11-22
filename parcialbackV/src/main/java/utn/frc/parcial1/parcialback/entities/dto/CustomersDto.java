package utn.frc.parcial1.parcialback.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CustomersDto {
    private Long customersId;
    private String firstName;
    private String lastName;
    private String company;
    private String address;
    private String email;

}
