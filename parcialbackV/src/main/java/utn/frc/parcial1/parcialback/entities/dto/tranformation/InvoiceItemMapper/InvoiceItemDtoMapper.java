package utn.frc.parcial1.parcialback.entities.dto.tranformation.InvoiceItemMapper;

import org.springframework.stereotype.Service;
import utn.frc.parcial1.parcialback.entities.InvoiceItems;
import utn.frc.parcial1.parcialback.entities.dto.InvoiceItemsDto;

import java.util.function.Function;

@Service
public class InvoiceItemDtoMapper implements Function<InvoiceItems, InvoiceItemsDto> {
    @Override
    public InvoiceItemsDto apply(InvoiceItems invoiceItems){
        return new InvoiceItemsDto(invoiceItems.getInvoiceLineId(), invoiceItems.getInvoiceId(),
                invoiceItems.getTrackId(), invoiceItems.getUnitPrice(),invoiceItems.getQuantity(),
                invoiceItems.getInvoices(),invoiceItems.getTracks());
    }
}
