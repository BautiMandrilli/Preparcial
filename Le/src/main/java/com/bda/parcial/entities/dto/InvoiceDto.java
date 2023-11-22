package com.bda.parcial.entities.dto;


import com.bda.parcial.entities.InvoiceItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDto {

    private Long id;
    private Long customerId;
    private LocalDateTime invoiceDate;
    private String billingCountry;
    private int total;
    private List<InvoiceItem> invoiceItems;

}
