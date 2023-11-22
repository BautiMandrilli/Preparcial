package ar.edu.utn.frc.backend.services;

import ar.edu.utn.frc.backend.models.Invoices;
import ar.edu.utn.frc.backend.repositories.InvoicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {
    @Autowired
    private InvoicesRepository invoiceRepository;

    public List<Invoices> findAll(){
        return invoiceRepository.findAll();
    }

    public Optional<Invoices> findById(Long id) {
        return invoiceRepository.findById(id);
    }

    public Invoices save(Invoices invoice){
        return invoiceRepository.save(invoice);
    }

    public Invoices update(Invoices invoice){
        Long id = invoice.getInvoiceId();
        Optional<Invoices> existingInvoice = invoiceRepository.findById(id);
        if (existingInvoice.isPresent()){
            return invoiceRepository.save(invoice);
        } else {
            return null;
        }
    }

    public List<Invoices> findByCustomerId(long customerId) {
        return invoiceRepository.findByCustomerId(customerId);
    }

    public boolean deleteById(Long id){
        if (invoiceRepository.existsById(id)) {
            invoiceRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
