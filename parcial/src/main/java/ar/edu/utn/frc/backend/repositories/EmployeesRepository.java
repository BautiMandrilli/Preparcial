package ar.edu.utn.frc.backend.repositories;

import ar.edu.utn.frc.backend.models.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface EmployeesRepository extends JpaRepository <Employees, Long> {
}
