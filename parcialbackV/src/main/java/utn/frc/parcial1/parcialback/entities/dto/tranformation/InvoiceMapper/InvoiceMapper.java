package utn.frc.parcial1.parcialback.entities.dto.tranformation.InvoiceMapper;

import org.springframework.stereotype.Service;
import utn.frc.parcial1.parcialback.entities.Invoices;
import utn.frc.parcial1.parcialback.entities.dto.InvoiceDto;

import java.util.function.Function;

@Service
public class InvoiceMapper implements Function<InvoiceDto, Invoices> {
    @Override
    public Invoices apply(InvoiceDto invoiceDto){
        return new Invoices(invoiceDto.getInvoiceId(),invoiceDto.getCustomerId(),invoiceDto.getInvoiceDate(),
                invoiceDto.getBillingAddress(),invoiceDto.getBillingCity(),invoiceDto.getBillingState(),
                invoiceDto.getBillingCountry(),invoiceDto.getBillingPostalCode(),invoiceDto.getTotal(),
                invoiceDto.getInvoiceLineId());
    }
}
