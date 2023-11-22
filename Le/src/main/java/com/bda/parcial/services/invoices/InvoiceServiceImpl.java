package com.bda.parcial.services.invoices;

import com.bda.parcial.entities.Invoice;
import com.bda.parcial.entities.dto.InvoiceDto;
import com.bda.parcial.entities.dto.transformations.invoiceT.InvoiceDtoMapper;
import com.bda.parcial.entities.dto.transformations.invoiceT.InvoiceMapper;
import com.bda.parcial.repositories.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;

    private final InvoiceMapper invoiceMapper;

    private final InvoiceDtoMapper invoiceDtoMapper;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, InvoiceMapper invoiceMapper, InvoiceDtoMapper invoiceDtoMapper) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceMapper = invoiceMapper;
        this.invoiceDtoMapper = invoiceDtoMapper;
    }

    @Override
    public void add(InvoiceDto entity) {
        Invoice invoiceNuevo = invoiceMapper.apply(entity);
        invoiceRepository.save(invoiceNuevo);

    }
    @Override
    public InvoiceDto getById(Long id) {
        Optional<Invoice> invoice = invoiceRepository.findById(id);
        return invoice.map(invoiceDtoMapper).orElseThrow();
    }

    @Override
    public List<InvoiceDto> getAll() {
        List<Invoice> values = invoiceRepository.findAll();
        return values.stream().map(invoiceDtoMapper).toList();
    }

    @Override
    public InvoiceDto delete(Long id) {
        Optional<Invoice> invoice = invoiceRepository.findById(id);
        invoice.ifPresent(invoiceRepository::delete);
        return invoice.map(invoiceDtoMapper).orElseThrow();
    }

    @Override
    public void update(InvoiceDto entity, Long id) {

        Optional<Invoice> invoice = invoiceRepository.findById(id);
        Invoice invoiceExistente = invoice.get();

        invoiceExistente.setCustomerId(entity.getCustomerId());
        invoiceExistente.setInvoiceDate(entity.getInvoiceDate());
        invoiceExistente.setBillingCountry(entity.getBillingCountry());
        invoiceExistente.setTotal(entity.getTotal());

        invoiceRepository.save(invoiceExistente);

    }
}
