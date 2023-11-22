package utn.frc.parcial1.parcialback.entities.dto.tranformation.InvoiceItemMapper;

import org.springframework.stereotype.Service;
import utn.frc.parcial1.parcialback.entities.InvoiceItems;
import utn.frc.parcial1.parcialback.entities.dto.InvoiceItemsDto;

import java.util.function.Function;

@Service
public class InvoiceItemMapper implements Function<InvoiceItemsDto, InvoiceItems> {
    @Override
    public InvoiceItems apply(InvoiceItemsDto invoiceItemsDto){
        return new InvoiceItems(invoiceItemsDto.getInvoiceLineId(), invoiceItemsDto.getInvoiceId(),
                invoiceItemsDto.getTrackId(), invoiceItemsDto.getUnitPrice(), invoiceItemsDto.getQuantity(),
                invoiceItemsDto.getInvoices(),invoiceItemsDto.getTracks());
    }

}
