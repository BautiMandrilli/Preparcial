package utn.frc.parcial1.parcialback.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Table(name = "invoices")
@NoArgsConstructor
@Entity

public class Invoices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableGenerator(name = "invoices", table = "sqlite_sequence",
            pkColumnName = "name", valueColumnName = "seq",
            pkColumnValue="invoices", allocationSize=1)
    @Column(name = "InvoiceId",nullable = false)
    private Long invoiceId;

    @Column(name = "CustomerId",nullable = false)
    private Long customerId;

    @Column(name = "InvoiceDate",nullable = false)
    private Date invoiceDate;

    @Column(name = "BillingAddress")
    private String billingAddress;

    @Column(name = "BillingCity")
    private  String billingCity;

    @Column(name = "BillingState")
    private  String billingState;

    @Column(name = "BillingCountry")
    private  String billingCountry;

    @Column(name = "BillingPostalCode")
    private String billingPostalCode;

    @Column(name = "Total", nullable = false)
    private int total;

    @OneToMany(mappedBy = "invoices", fetch = FetchType.LAZY)
    private List<InvoiceItems> invoiceLineId;


    public Invoices(Long invoiceId, Long customerId, Date invoiceDate, String billingAddress, String billingCity,
                    String billingState, String billingCountry, String billingPostalCode,
                    int total,List<InvoiceItems> invoiceLineId) {
        this.invoiceId=invoiceId;
        this.customerId=customerId;
        this.invoiceDate=invoiceDate;
        this.billingAddress=billingAddress;
        this.billingCity=billingCity;
        this.billingState=billingState;
        this.billingCountry=billingCountry;
        this.billingPostalCode=billingPostalCode;
        this.total=total;
        this.invoiceLineId=invoiceLineId;
    }
}
