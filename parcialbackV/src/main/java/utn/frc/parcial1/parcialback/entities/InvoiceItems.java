package utn.frc.parcial1.parcialback.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "invoice_items")
@Data
@NoArgsConstructor

public class InvoiceItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableGenerator(name = "invoices_items", table = "sqlite_sequence",
            pkColumnName = "name", valueColumnName = "seq",
            pkColumnValue="invoices_items", allocationSize=1)
    @Column(name = "InvoiceLineId",nullable = false)
    private Long invoiceLineId;

    @Column(name = "InvoiceId",nullable = false,insertable=false, updatable=false)
    private Long invoiceId;

    @Column(name = "TrackId",nullable = false,insertable=false, updatable=false)
    private Long trackId;

    @Column(name = "UnitPrice",nullable = false)
    private float unitPrice;

    @Column(name = "Quantity",nullable = false)
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "InvoiceId")
    @JsonIgnore
    private Invoices invoices;

    @ManyToOne
    @JoinColumn(name = "TrackId")
    @JsonIgnore
    private Tracks tracks;


    public InvoiceItems(Long invoiceLineId, Long invoiceId, Long trackId, float unitPrice, int quantity,
                        Invoices invoices, Tracks tracks) {
        this.invoiceLineId=invoiceLineId;
        this.invoiceId=invoiceId;
        this.trackId=trackId;
        this.unitPrice=unitPrice;
        this.quantity=quantity;
        this.invoices=invoices;
        this.tracks=tracks;
    }
}