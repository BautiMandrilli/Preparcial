package ar.edu.utn.frc.backend.controllers;


import ar.edu.utn.frc.backend.models.PlaylistTrack;
import ar.edu.utn.frc.backend.services.PlaylistTrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/playlistTrack")
public class PlaylistTrackController {
    @Autowired
    private PlaylistTrackService playlistTrackService;

    @PostMapping
    public ResponseEntity<PlaylistTrack> createCustomer(@RequestBody PlaylistTrack playlistTrack){
        PlaylistTrack createdPlaylistTrack = playlistTrackService.save(playlistTrack);
        return ResponseEntity.ok(createdPlaylistTrack);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id){
        playlistTrackService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
