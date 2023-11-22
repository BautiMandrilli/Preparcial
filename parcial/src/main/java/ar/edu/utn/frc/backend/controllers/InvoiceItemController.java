package ar.edu.utn.frc.backend.controllers;


import ar.edu.utn.frc.backend.models.InvoiceItems;
import ar.edu.utn.frc.backend.services.InvoiceItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/invoiceItems")
public class InvoiceItemController {

        @Autowired
        private InvoiceItemService invoiceItemService;

        @GetMapping
        public ResponseEntity<List<InvoiceItems>> getAllInvoiceItems(){
            List<InvoiceItems> invoiceItems = invoiceItemService.findAll();
            return ResponseEntity.ok(invoiceItems);
        }

        @GetMapping("/{id}")
        public ResponseEntity<Optional<InvoiceItems>> getInvoiceItemById(@PathVariable Long id) {
            Optional<InvoiceItems> invoiceItem = invoiceItemService.findById(id);

            if(invoiceItem.isPresent()){
                return ResponseEntity.ok(invoiceItem);
            } else {
                return  ResponseEntity.notFound().build();
            }
        }

        @PostMapping
        public ResponseEntity<InvoiceItems> createInvoiceItem(@RequestBody InvoiceItems invoiceItem){
            InvoiceItems createdInvoiceItem = invoiceItemService.save(invoiceItem);
            return ResponseEntity.ok(createdInvoiceItem);
        }

        @PutMapping
        public ResponseEntity<InvoiceItems> updateInvoiceItem(@RequestBody InvoiceItems invoiceItem){
            InvoiceItems updatedInvoiceItem = invoiceItemService.update(invoiceItem);
            if (updatedInvoiceItem != null){
                return ResponseEntity.ok(updatedInvoiceItem);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteInvoiceItem(@PathVariable Long id){
            boolean deleted = invoiceItemService.deleteById(id);
            if (deleted) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        }

}
