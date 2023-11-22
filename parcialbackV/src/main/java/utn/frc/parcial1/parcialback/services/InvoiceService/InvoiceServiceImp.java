package utn.frc.parcial1.parcialback.services.InvoiceService;

import org.springframework.stereotype.Service;
import utn.frc.parcial1.parcialback.entities.Invoices;
import utn.frc.parcial1.parcialback.entities.dto.InvoiceDto;
import utn.frc.parcial1.parcialback.entities.dto.tranformation.InvoiceMapper.InvoiceDtoMapper;
import utn.frc.parcial1.parcialback.entities.dto.tranformation.InvoiceMapper.InvoiceMapper;
import utn.frc.parcial1.parcialback.repositories.InvoicesRepository;


import java.util.List;
import java.util.Optional;

@Service
public class InvoiceServiceImp implements InvoiceService{
    private final InvoicesRepository invoicesRepository;
    private final InvoiceDtoMapper invoiceDtoMapper;
    private final InvoiceMapper invoiceMapper;

    public InvoiceServiceImp(InvoicesRepository invoicesRepository,
                                 InvoiceDtoMapper invoiceDtoMapper,
                                 InvoiceMapper invoiceMapper) {
        this.invoicesRepository = invoicesRepository;
        this.invoiceDtoMapper = invoiceDtoMapper;
        this.invoiceMapper = invoiceMapper;
    }

    @Override
    public void add(InvoiceDto entity) {
        Invoices invoices = Optional.of(entity).map(invoiceMapper).get();
        invoicesRepository.save(invoices);
    }

    @Override
    public InvoiceDto getById(Long id){
        Optional<Invoices> invoices = invoicesRepository.findById(id);
        return invoices
                .map(invoiceDtoMapper)
                .orElseThrow();
    }
    @Override
    public List<InvoiceDto> getAll() {
        List<Invoices> values = invoicesRepository.findAll();
        return values
                .stream()
                .map(invoiceDtoMapper)
                .toList();
    }
    @Override
    public InvoiceDto delete(Long id){
        Optional<Invoices> optionalInvoices = invoicesRepository
                .findById(id);
        optionalInvoices.ifPresent(invoicesRepository::delete);
        return optionalInvoices
                .map(invoiceDtoMapper)
                .orElseThrow();
    }

    @Override
    public void update(InvoiceDto entity, Long id) {
        Optional<Invoices> invoiceItems = invoicesRepository.findById(id);
        Invoices invoicesExistente = invoiceItems.get();
        invoicesExistente.setCustomerId(entity.getCustomerId());
        invoicesExistente.setInvoiceDate(entity.getInvoiceDate());
        invoicesExistente.setBillingAddress(entity.getBillingAddress());
        invoicesExistente.setBillingState(entity.getBillingState());
        invoicesExistente.setBillingCountry(entity.getBillingCountry());
        invoicesExistente.setBillingPostalCode(entity.getBillingPostalCode());
        invoicesExistente.setTotal(entity.getTotal());
        invoicesRepository.save(invoicesExistente);
    }
}

