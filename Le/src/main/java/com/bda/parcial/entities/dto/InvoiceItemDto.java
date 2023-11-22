package com.bda.parcial.entities.dto;


import com.bda.parcial.entities.Invoice;
import com.bda.parcial.entities.Track;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceItemDto {

    private Long id;
    private Long invoiceId;
    private Long trackId;
    private float unitPrice;
    private int quantity;
    private Track track;
    private Invoice invoice;
}
