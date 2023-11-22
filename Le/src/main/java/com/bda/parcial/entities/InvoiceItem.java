package com.bda.parcial.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "invoice_items")
@Data
@NoArgsConstructor
public class InvoiceItem {

    @Id
    @GeneratedValue(generator = "invoices_items")
    @TableGenerator(name = "invoices_items", table = "sqlite_sequence",
            pkColumnName = "name", valueColumnName = "seq",
            pkColumnValue="invoice_items", allocationSize=1)

    @Column(name = "InvoiceLineId", nullable = false)
    private Long id;

    @Column(name = "InvoiceId", nullable = false, insertable=false, updatable=false)
    private Long invoiceId;

    @Column(name = "TrackId", nullable = false, insertable=false, updatable=false)
    private Long trackId;

    @Column(name = "UnitPrice", nullable = false)
    private float unitPrice;

    @Column(name = "Quantity", nullable = false)
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "TrackId")
    @JsonIgnore
    private Track track;

    @ManyToOne
    @JoinColumn(name = "InvoiceId")
    @JsonIgnore
    private Invoice invoice;

    public InvoiceItem(Long id, Long invoiceId, Long trackId, float unitPrice, int quantity, Track track, Invoice invoice) {
        this.id = id;
        this.invoiceId = invoiceId;
        this.trackId = trackId;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.track = track;
        this.invoice = invoice;
    }
}
