package utn.frc.parcial1.parcialback.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "customers")
@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Entity
public class Customers {
    @Id
    @GeneratedValue(generator = "customers")
    @TableGenerator(name = "customers", table = "sqlite_sequence",
            pkColumnName = "name", valueColumnName = "seq",
            pkColumnValue="customers",
            allocationSize=1)
    @Column(name = "CustomerId", nullable = false)
    private Long customerId;

    @Column(name = "FirstName",nullable = false)
    private String firstName;

    @Column(name = "LastName",nullable = false)
    private String lastName;

    @Column(name = "Company")
    private String company;

    @Column(name = "Address")
    private String address;

    @Column(name = "City")
    private String city;

    @Column(name = "State")
    private String state;

    @Column(name = "PostalCode")
    private String postalCode;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "Fax")
    private String fax;

    @Column(name = "Email",nullable = false)
    private String email;
    public Customers(Long customersId, String firstName, String lastName, String company, String address,String email) {
        this.customerId=customersId;
        this.firstName=firstName;
        this.lastName=lastName;
        this.company=company;
        this.address=address;
        this.email=email;
    }

}
