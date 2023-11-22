package com.bda.parcial.controllers;


import com.bda.parcial.entities.dto.PlaylistDto;
import com.bda.parcial.entities.dto.TrackDto;
import com.bda.parcial.request.PlaylistCreationRequest;
import com.bda.parcial.services.playlist.PlaylistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/playlists")
public class PlaylistController {

    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @PostMapping("/add")
    public ResponseEntity<PlaylistDto> add(@RequestBody PlaylistDto entity) {
        playlistService.add(entity);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<PlaylistDto>> getAll() {
        List<PlaylistDto> values = playlistService.getAll();
        return ResponseEntity.ok(values);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PlaylistDto> getById(@PathVariable("id") Long id) {
        PlaylistDto playlistDto = playlistService.getById(id);
        return ResponseEntity.ok(playlistDto);
    }

    @PostMapping
    public ResponseEntity<PlaylistDto> addPlaylistByParams(@RequestBody PlaylistCreationRequest playlistCreationRequest) {
        playlistService.addPlaylistByParams(playlistCreationRequest.getName(), playlistCreationRequest.getCustomerId(),
                playlistCreationRequest.getGenreId(), playlistCreationRequest.getComposerFilter(),
                playlistCreationRequest.getDuracionMaxima());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody PlaylistDto entity, @PathVariable ("id") Long id) {
        playlistService.update(entity, id);
        return ResponseEntity.ok().build();

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable ("id") Long id) {
        PlaylistDto playlistDto = playlistService.delete(id);
        return ResponseEntity.ok().build();

    }

}
