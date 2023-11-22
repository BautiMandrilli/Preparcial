package ar.edu.utn.frc.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "invoice_items")
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class InvoiceItems {

    @JoinColumn(name = "InvoiceId")
    private long invoiceId;

    @JoinColumn(name = "TrackId")
    private long trackId;

    @Column(name = "UnitPrice")
    private double unitPrice;

    @Column(name = "Quantity")
    private long quantity;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "InvoiceLineId")
    private long invoiceLineId;
}
