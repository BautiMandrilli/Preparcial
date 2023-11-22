package com.bda.parcial.entities.dto.transformations.invoiceT;

import com.bda.parcial.entities.Invoice;
import com.bda.parcial.entities.dto.InvoiceDto;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class InvoiceDtoMapper implements Function<Invoice, InvoiceDto> {

    @Override
    public InvoiceDto apply(Invoice invoice) {
        return new InvoiceDto(invoice.getId(), invoice.getCustomerId(),
                invoice.getInvoiceDate(),
                invoice.getBillingCountry(),
                invoice.getTotal(), invoice.getInvoiceItems()
                );
    }
}
