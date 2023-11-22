package com.bda.parcial.entities.dto.transformations.invoiceT;

import com.bda.parcial.entities.Invoice;
import com.bda.parcial.entities.dto.InvoiceDto;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class InvoiceMapper implements Function<InvoiceDto, Invoice> {


    @Override
    public Invoice apply(InvoiceDto invoiceDto) {
        return new Invoice(invoiceDto.getId(),
                invoiceDto.getCustomerId(),
                invoiceDto.getInvoiceDate(),
                invoiceDto.getBillingCountry(),
                invoiceDto.getTotal(), invoiceDto.getInvoiceItems());
    }
}
