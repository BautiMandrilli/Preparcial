package ar.edu.utn.frc.backend.services;

import ar.edu.utn.frc.backend.models.PlaylistTrack;
import ar.edu.utn.frc.backend.repositories.PlaylistTracksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PlaylistTrackService {

    @Autowired
    private PlaylistTracksRepository playlistsTrackRepository;

    public PlaylistTrack save(PlaylistTrack playlistTrack){
        return playlistsTrackRepository.save(playlistTrack);
    }

    public void deleteById(Long id){
        playlistsTrackRepository.deleteById(id);
    }
}