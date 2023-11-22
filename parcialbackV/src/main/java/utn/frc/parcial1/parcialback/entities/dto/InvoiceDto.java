package utn.frc.parcial1.parcialback.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import utn.frc.parcial1.parcialback.entities.InvoiceItems;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDto {
    private Long invoiceId;
    private Long customerId;
    private Date invoiceDate;
    private String billingAddress;
    private  String billingCity;
    private  String billingState;
    private  String billingCountry;
    private String billingPostalCode;
    private int total;
    private List<InvoiceItems> invoiceLineId;
}
