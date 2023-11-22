package utn.frc.parcial1.parcialback.entities.dto.tranformation.InvoiceMapper;


import org.springframework.stereotype.Service;
import utn.frc.parcial1.parcialback.entities.Invoices;
import utn.frc.parcial1.parcialback.entities.dto.InvoiceDto;

import java.util.function.Function;

@Service
public class InvoiceDtoMapper implements Function<Invoices, InvoiceDto> {
    @Override
    public InvoiceDto apply(Invoices invoices) {
        return new InvoiceDto(invoices.getInvoiceId(), invoices.getCustomerId(), invoices.getInvoiceDate(),
                invoices.getBillingAddress(),invoices.getBillingCity(),invoices.getBillingState(),
                invoices.getBillingCountry(),invoices.getBillingPostalCode(),invoices.getTotal(),
                invoices.getInvoiceLineId());
    }
}
