package utn.frc.parcial1.parcialback.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.frc.parcial1.parcialback.entities.dto.PlaylistDto;
import utn.frc.parcial1.parcialback.services.playlistService.PlaylistService;


import java.util.List;

@RestController
@RequestMapping("api/playlists")
public class PlaylistController {
    private  final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlaylistDto> getById(@PathVariable("id") Long id) {
        PlaylistDto res = playlistService.getById(id);
        return ResponseEntity.ok(res);
    }
    @GetMapping
    public ResponseEntity<List<PlaylistDto>> getAll(){
        List<PlaylistDto> values = playlistService.getAll();
        return ResponseEntity.ok(values);
    }

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody PlaylistDto entity) {
        playlistService.add(entity);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PostMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody PlaylistDto entity,
                                       @PathVariable("id") Long id){
        playlistService.update(entity, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id")Long id) {
        playlistService.delete(id);
        return ResponseEntity.ok().build();
    }
}

