package ar.edu.utn.frc.backend.controllers;

import ar.edu.utn.frc.backend.models.InvoiceItems;
import ar.edu.utn.frc.backend.models.Invoices;
import ar.edu.utn.frc.backend.models.Tracks;
import ar.edu.utn.frc.backend.models.dto.TracksDto;
import ar.edu.utn.frc.backend.services.InvoiceItemService;
import ar.edu.utn.frc.backend.services.InvoiceService;
import ar.edu.utn.frc.backend.services.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tracks")
public class TrackController {
    @Autowired
    private TrackService trackService;
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private InvoiceItemService invoiceItemService;


    @GetMapping("/all")
    public ResponseEntity<List<Tracks>> getAllTracks(){
        List<Tracks> tracks = trackService.findAll();
        return ResponseEntity.ok(tracks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Tracks>> getTrackById(@PathVariable Long id) {
        Optional<Tracks> track = trackService.findById(id);
        if(track.isPresent()){
            return ResponseEntity.ok(track);
        } else {
            return  ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<TracksDto>> getTracksByCustomerAndGenre(
            @RequestParam("customerid") Long customerId,
            @RequestParam("genreid") Long genreId
    ) {
        List<Invoices> invoices =  invoiceService.findByCustomerId(customerId);
        if (invoices.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<InvoiceItems> invoicesItems = invoiceItemService.findInvoiceItemsByInvoices(invoices);
        List<TracksDto> tracksDtos = trackService.getTracksByGenreAndCustomer(invoicesItems, genreId);
        if (tracksDtos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tracksDtos);
    }

    @PostMapping
    public ResponseEntity<Tracks> createTrack(@RequestBody Tracks track){
        Tracks createdTrack = trackService.save(track);
        return ResponseEntity.ok(createdTrack);
    }

    @PutMapping
    public ResponseEntity<Tracks> updateTrack(@RequestBody Tracks track){
        Tracks updatedTrack = trackService.update(track);
        if (updatedTrack != null){
            return ResponseEntity.ok(updatedTrack);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrack(@PathVariable Long id){
        boolean deleted = trackService.deleteById(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
