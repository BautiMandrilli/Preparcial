package com.bda.parcial.controllers;


import com.bda.parcial.entities.dto.InvoiceItemDto;
import com.bda.parcial.entities.dto.TrackDto;
import com.bda.parcial.services.track.TrackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tracks")
public class TrackController {

    private final TrackService trackService;

    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @GetMapping
    public ResponseEntity<List<TrackDto>> getTracks(@RequestParam(value = "customerid", required = false) Long customerId,
                                                    @RequestParam(value= "genreid", required = false) Long genreId) {
        List<TrackDto> values;

        if (customerId != null) {
            if (genreId != null) {
                values = trackService.getTracksByCustomerIdAndGenreId(customerId, genreId);
            } else {
                values = trackService.getTracksByCustomerId(customerId);
            }
        } else if (genreId != null) {
            values = trackService.getTracksByGenreId(genreId);
        } else {
            values = trackService.getAll();
        }

        if (values.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
        return ResponseEntity.ok(values);}
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrackDto> getById(@PathVariable("id") Long id) {
        TrackDto track = trackService.getById(id);
        return ResponseEntity.ok(track);
    }

    @PostMapping
    public ResponseEntity<TrackDto> add(@RequestBody TrackDto entity) {
        trackService.add(entity);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody TrackDto entity, @PathVariable ("id") Long id) {
        trackService.update(entity, id);
        return ResponseEntity.ok().build();

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable ("id") Long id) {
        TrackDto trackItem = trackService.delete(id);
        return ResponseEntity.ok().build();

    }


}
