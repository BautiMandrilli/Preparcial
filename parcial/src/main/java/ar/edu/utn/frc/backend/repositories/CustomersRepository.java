package ar.edu.utn.frc.backend.repositories;

import ar.edu.utn.frc.backend.models.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomersRepository extends JpaRepository <Customers, Long> {
}
