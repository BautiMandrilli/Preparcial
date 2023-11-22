package ar.edu.utn.frc.backend.controllers;

import ar.edu.utn.frc.backend.models.Playlists;
import ar.edu.utn.frc.backend.services.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/playlist")
public class PlaylistController {
    @Autowired
    private PlaylistService playlistService;

    @GetMapping
    public ResponseEntity<List<Playlists>> getAllPlaylists(){
        List<Playlists> playlists = playlistService.findAll();
        return ResponseEntity.ok(playlists);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Playlists>> getPlaylistById(@PathVariable Long id) {
        Optional<Playlists> playlist = playlistService.findById(id);
        if(playlist.isPresent()){
            return ResponseEntity.ok(playlist);
        } else {
            return  ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/new")
    public ResponseEntity<Playlists> createPlaylist(@RequestBody Playlists playlist){
        Playlists createdPlaylist = playlistService.save(playlist);
        return ResponseEntity.ok(createdPlaylist);
    }

    @PostMapping
    public ResponseEntity<Playlists> createPlaylist(
            @RequestParam String playlistName,
            @RequestParam Long customerId,
            @RequestParam Long genreId,
            @RequestParam String composerFilter,
            @RequestParam Long maxDuration
    ) {
        Playlists playlist = playlistService.createPlaylist(
                playlistName,
                customerId,
                genreId,
                composerFilter,
                maxDuration
        );
        if (playlist != null) {
            return new ResponseEntity<>(playlist, HttpStatus.CREATED);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping
        public ResponseEntity<Playlists> updatePlaylist(@RequestBody Playlists playlist){
            Playlists updatedPlaylist = playlistService.update(playlist);
            if (updatedPlaylist != null){
                return ResponseEntity.ok(updatedPlaylist);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlaylist(@PathVariable Long id){
        boolean deleted = playlistService.deleteById(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
