package utn.frc.parcial1.parcialback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utn.frc.parcial1.parcialback.entities.InvoiceItems;

import java.util.List;

@Repository
public interface InvoiceItemsRepository extends JpaRepository<InvoiceItems, Long> {
    //List<InvoiceItems> findByInvoiceIdIn(List<Long> invoiceIds);
}
