package utn.frc.parcial1.parcialback.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import utn.frc.parcial1.parcialback.entities.Invoices;
import utn.frc.parcial1.parcialback.entities.Tracks;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceItemsDto {
    private Long invoiceLineId;
    private Long invoiceId;
    private Long trackId;
    private float unitPrice;
    private int quantity;
    private Invoices invoices;
    private Tracks tracks;
}
