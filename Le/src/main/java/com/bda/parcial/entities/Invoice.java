package com.bda.parcial.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "invoices")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Invoice {
    @Id
    @GeneratedValue(generator = "invoices")
    @TableGenerator(name = "invoices", table = "sqlite_sequence",
            pkColumnName = "name", valueColumnName = "seq",
            pkColumnValue="invoices",
            allocationSize=1)

    @Column(name = "InvoiceId", nullable = false)
    private Long id;

    @Column(name = "CustomerId", nullable = false)
    private Long customerId;

    @Column(name = "InvoiceDate", nullable = false)
    private LocalDateTime invoiceDate;

    @Column(name = "BillingAddress")
    private String billingAddress;

    @Column(name = "BillingCity")
    private String billingCity;

    @Column(name = "BillingState")
    private String billingState;

    @Column(name = "BillingCountry")
    private String billingCountry;

    @Column(name = "BillingPostalCode")
    private String billingPostalCode;

    @Column(name = "Total", nullable = false)
    private int total;

    @OneToMany(mappedBy = "invoice", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<InvoiceItem> invoiceItems;

    public Invoice(Long id, Long customerId, LocalDateTime invoiceDate, String billingCountry, int total,List<InvoiceItem> invoiceItems) {
        this.id = id;
        this.customerId = customerId;
        this.invoiceDate = invoiceDate;
        this.billingCountry = billingCountry;
        this.total = total;
        this.invoiceItems = invoiceItems;
    }
}
