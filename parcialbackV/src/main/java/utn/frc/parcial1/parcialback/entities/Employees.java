package utn.frc.parcial1.parcialback.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "employees ")
public class Employees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableGenerator(name = "employees", table = "sqlite_sequence",
        pkColumnName = "name", valueColumnName = "seq",
        pkColumnValue="employees", allocationSize=1)
    @Column(name = "EmployeeId", nullable = false)
    private Long emplyeeId;

    @Column(name = "FirstName",nullable = false)
    private String firstName;

    @Column(name = "LastName",nullable = false)
    private String lastName;

    @Column(name = "Title")
    private String title;

    @Column(name = "ReportsTo")
    private Long reportTo;

    @Column(name = "BirthDate")
    private Date birthDate;

    @Column(name = "HireDate")
    private Date hireDate;

    @Column(name = "Address")
    private String address;

    @Column(name = "City")
    private String city;

    @Column(name = "State")
    private String state;

    @Column(name = "Country")
    private String country;

    @Column(name = "PostalCode")
    private String postalCode;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "Fax")
    private String fax;

    @Column(name = "Email")
    private String email;

}
