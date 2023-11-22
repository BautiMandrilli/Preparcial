package com.bda.parcial.entities.dto.transformations.invoiceItemsT;

import com.bda.parcial.entities.InvoiceItem;
import com.bda.parcial.entities.dto.InvoiceItemDto;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class InvoiceItemDtoMapper implements Function<InvoiceItem, InvoiceItemDto> {
    @Override
    public InvoiceItemDto apply(InvoiceItem invoiceItem) {
        return new InvoiceItemDto(invoiceItem.getId(), invoiceItem.getInvoiceId(),
                invoiceItem.getTrackId(), invoiceItem.getUnitPrice(), invoiceItem.getQuantity(),
                invoiceItem.getTrack(), invoiceItem.getInvoice());
    }
}
