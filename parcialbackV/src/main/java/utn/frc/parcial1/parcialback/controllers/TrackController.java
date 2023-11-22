package utn.frc.parcial1.parcialback.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.frc.parcial1.parcialback.entities.dto.TrackDto;
import utn.frc.parcial1.parcialback.services.trackService.TrackService;

import java.util.List;

@RestController
@RequestMapping("api/tracks")
public class TrackController {
    private  final TrackService trackService;

    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrackDto> getById(@PathVariable("id") Long id) {
        TrackDto res = trackService.getById(id);
        return ResponseEntity.ok(res);
    }
    @GetMapping
    public ResponseEntity<List<TrackDto>> getAll(){
        List<TrackDto> values = trackService.getAll();
        return ResponseEntity.ok(values);
    }

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody TrackDto entity) {
        trackService.add(entity);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PostMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody TrackDto entity,
                                       @PathVariable("id") Long id){
        trackService.update(entity, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id")Long id) {
        trackService.delete(id);
        return ResponseEntity.ok().build();
    }
    /*@GetMapping("?customerid={customerId}&genreid={genereId}>")
    public ResponseEntity getTracksByCustomerId(@PathVariable("customerId") Long customerId
    ,@PathVariable("genereId") Long genreId) {
        try {
            List values = trackService.getTracksByCustomerGenres(customerId,genreId);
            if (values.isEmpty()) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.ok(values);
            }
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }*/
}
