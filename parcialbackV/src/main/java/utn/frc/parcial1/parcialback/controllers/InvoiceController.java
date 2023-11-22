package utn.frc.parcial1.parcialback.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.frc.parcial1.parcialback.entities.dto.InvoiceDto;
import utn.frc.parcial1.parcialback.services.InvoiceService.InvoiceService;

import java.util.List;

@RestController
@RequestMapping("api/invoices")
public class InvoiceController {
    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceDto> getById(@PathVariable("id") Long id) {
        InvoiceDto res = invoiceService.getById(id);
        return ResponseEntity.ok(res);
    }
    @GetMapping
    public ResponseEntity<List<InvoiceDto>> getAll(){
        List<InvoiceDto> values = invoiceService.getAll();
        return ResponseEntity.ok(values);
    }

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody InvoiceDto entity) {
        invoiceService.add(entity);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PostMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody InvoiceDto entity,
                                       @PathVariable("id") Long id){
        invoiceService.update(entity, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id")Long id) {
        invoiceService.delete(id);
        return ResponseEntity.ok().build();
    }
}
