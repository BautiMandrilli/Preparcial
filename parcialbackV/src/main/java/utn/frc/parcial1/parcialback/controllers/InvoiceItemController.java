package utn.frc.parcial1.parcialback.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.frc.parcial1.parcialback.entities.dto.InvoiceItemsDto;
import utn.frc.parcial1.parcialback.services.InvoiceItemsService.InvoiceItemService;


import java.util.List;

@RestController
@RequestMapping("api/invoiceItems")
public class InvoiceItemController {
    private  final InvoiceItemService invoiceItemService;

    public InvoiceItemController(InvoiceItemService invoiceItemService) {
        this.invoiceItemService = invoiceItemService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceItemsDto> getById(@PathVariable("id") Long id) {
        InvoiceItemsDto res = invoiceItemService.getById(id);
        return ResponseEntity.ok(res);
    }
    @GetMapping
    public ResponseEntity<List<InvoiceItemsDto>> getAll(){
        List<InvoiceItemsDto> values = invoiceItemService.getAll();
        return ResponseEntity.ok(values);
    }

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody InvoiceItemsDto entity) {
        invoiceItemService.add(entity);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PostMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody InvoiceItemsDto entity,
                                       @PathVariable("id") Long id){
        invoiceItemService.update(entity, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id")Long id) {
        invoiceItemService.delete(id);
        return ResponseEntity.ok().build();
    }
}
