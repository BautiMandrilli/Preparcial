package com.bda.parcial.controllers;


import com.bda.parcial.entities.Invoice;
import com.bda.parcial.entities.dto.CustomerDto;
import com.bda.parcial.entities.dto.InvoiceDto;
import com.bda.parcial.services.invoices.InvoiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping
    public ResponseEntity<List<InvoiceDto>> getAll() {
        List<InvoiceDto> values = invoiceService.getAll();
        return ResponseEntity.ok(values);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceDto> getById(@PathVariable ("id") Long id) {
        InvoiceDto invoice = invoiceService.getById(id);
        return ResponseEntity.ok(invoice);
    }

    @PostMapping
    public ResponseEntity<InvoiceDto> add(@RequestBody InvoiceDto entity) {
        invoiceService.add(entity);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody InvoiceDto entity, @PathVariable ("id") Long id) {
        invoiceService.update(entity, id);
        return ResponseEntity.ok().build();

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable ("id") Long id) {
        InvoiceDto invoice = invoiceService.delete(id);
        return ResponseEntity.ok().build();

    }



}
