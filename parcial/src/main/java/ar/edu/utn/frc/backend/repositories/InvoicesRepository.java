package ar.edu.utn.frc.backend.repositories;

import ar.edu.utn.frc.backend.models.Invoices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoicesRepository extends JpaRepository <Invoices, Long> {
    @Query("SELECT i FROM Invoices i WHERE i.customerId = :customerId")
    List<Invoices> findByCustomerId(@Param("customerId") long customerId);
}
