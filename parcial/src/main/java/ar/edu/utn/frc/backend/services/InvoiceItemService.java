package ar.edu.utn.frc.backend.services;


import ar.edu.utn.frc.backend.models.InvoiceItems;
import ar.edu.utn.frc.backend.models.Invoices;
import ar.edu.utn.frc.backend.repositories.InvoiceItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceItemService {
    @Autowired
    private InvoiceItemsRepository invoiceItemsRepository;

    public List<InvoiceItems> findAll() {
        return invoiceItemsRepository.findAll();
    }

    public Optional<InvoiceItems> findById(Long id) {
        return invoiceItemsRepository.findById(id);
    }

    public List<InvoiceItems> findInvoiceItemsByInvoices(List<Invoices> invoicesList) {
        List<Long> invoiceIds = new ArrayList<>();
        for (Invoices invoice : invoicesList) {
            invoiceIds.add(invoice.getInvoiceId());
        }
        return invoiceItemsRepository.findByInvoiceIdIn(invoiceIds);
    }

    public InvoiceItems save(InvoiceItems invoiceItem) {
        return invoiceItemsRepository.save(invoiceItem);
    }

    public InvoiceItems update(InvoiceItems invoiceItem) {
        Long id = invoiceItem.getInvoiceLineId();
        Optional<InvoiceItems> existingInvoiceItem = invoiceItemsRepository.findById(id);
        if (existingInvoiceItem.isPresent()) {
            return invoiceItemsRepository.save(invoiceItem);
        } else {
            return null;
        }
    }

    public boolean deleteById(Long id) {
        if (invoiceItemsRepository.existsById(id)) {
            invoiceItemsRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}

