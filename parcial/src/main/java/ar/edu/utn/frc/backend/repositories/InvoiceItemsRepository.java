package ar.edu.utn.frc.backend.repositories;

import ar.edu.utn.frc.backend.models.InvoiceItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceItemsRepository extends JpaRepository <InvoiceItems, Long> {
    List<InvoiceItems> findByInvoiceIdIn(List<Long> invoiceIds);
}
