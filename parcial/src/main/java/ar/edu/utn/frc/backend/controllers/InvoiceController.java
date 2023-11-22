package ar.edu.utn.frc.backend.controllers;

import ar.edu.utn.frc.backend.models.Invoices;
import ar.edu.utn.frc.backend.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping
    public ResponseEntity<List<Invoices>> getAllInvoices(){
        List<Invoices> invoices = invoiceService.findAll();
        return ResponseEntity.ok(invoices);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Invoices>> getInvoiceById(@PathVariable Long id) {
        Optional<Invoices> invoice = invoiceService.findById(id);

        if(invoice.isPresent()){
            return ResponseEntity.ok(invoice);
        } else {
            return  ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Invoices> createInvoice(@RequestBody Invoices invoice){
        Invoices createdInvoice = invoiceService.save(invoice);
        return ResponseEntity.ok(createdInvoice);
    }

    @PutMapping
    public ResponseEntity<Invoices> updateInvoice(@RequestBody Invoices invoice){
        Invoices updatedInvoice = invoiceService.update(invoice);
        if (updatedInvoice != null){
            return ResponseEntity.ok(updatedInvoice);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlaylist(@PathVariable Long id){
        boolean deleted = invoiceService.deleteById(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
