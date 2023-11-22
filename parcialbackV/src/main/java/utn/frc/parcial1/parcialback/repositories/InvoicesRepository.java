package utn.frc.parcial1.parcialback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utn.frc.parcial1.parcialback.entities.Invoices;

import java.util.List;

@Repository
public interface InvoicesRepository extends JpaRepository<Invoices, Long> {
    //List<Invoices> findByInvoiceCustomerId(Long customerId);
}
