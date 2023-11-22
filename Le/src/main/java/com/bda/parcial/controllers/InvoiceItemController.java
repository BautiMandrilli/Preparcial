package com.bda.parcial.controllers;


import com.bda.parcial.entities.dto.InvoiceItemDto;
import com.bda.parcial.services.invoiceItems.InvoiceItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoiceitems")
public class InvoiceItemController {
    private final InvoiceItemService invoiceItemService;

    public InvoiceItemController(InvoiceItemService invoiceItemService) {
        this.invoiceItemService = invoiceItemService;
    }

    @GetMapping
    public ResponseEntity<List<InvoiceItemDto>> getAll() {
        List<InvoiceItemDto> values = invoiceItemService.getAll();
        return ResponseEntity.ok(values);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceItemDto> getById(@PathVariable("id") Long id) {
        InvoiceItemDto invoiceItem = invoiceItemService.getById(id);
        return ResponseEntity.ok(invoiceItem);
    }

    @PostMapping
    public ResponseEntity<InvoiceItemDto> add(@RequestBody InvoiceItemDto entity) {
        invoiceItemService.add(entity);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody InvoiceItemDto entity, @PathVariable ("id") Long id) {
        invoiceItemService.update(entity, id);
        return ResponseEntity.ok().build();

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable ("id") Long id) {
        InvoiceItemDto invoiceItem = invoiceItemService.delete(id);
        return ResponseEntity.ok().build();

    }
}
