package com.bda.parcial.entities.dto.transformations.invoiceItemsT;

import com.bda.parcial.entities.InvoiceItem;
import com.bda.parcial.entities.dto.InvoiceItemDto;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class InvoiceItemMapper implements Function<InvoiceItemDto, InvoiceItem> {
    @Override
    public InvoiceItem apply(InvoiceItemDto invoiceItemDto) {
        return new InvoiceItem(invoiceItemDto.getId(), invoiceItemDto.getInvoiceId(),
                invoiceItemDto.getTrackId(), invoiceItemDto.getUnitPrice(), invoiceItemDto.getQuantity(),
                invoiceItemDto.getTrack(), invoiceItemDto.getInvoice());
    }
}
