package ar.edu.utn.frc.backend.services;

import ar.edu.utn.frc.backend.models.Playlists;
import ar.edu.utn.frc.backend.repositories.PlaylistsRepository;
import ar.edu.utn.frc.backend.services.PlaylistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class PlaylistServiceTest {

    @InjectMocks
    private PlaylistService playlistService;

    @Mock
    private PlaylistsRepository playlistsRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAll() {
        List<Playlists> playlists = new ArrayList<>();
        playlists.add(new Playlists("Playlist 1", 1L));
        playlists.add(new Playlists("Playlist 2", 2L));

        Mockito.when(playlistsRepository.findAll()).thenReturn(playlists);

        List<Playlists> result = playlistService.findAll();

        assertEquals(2, result.size());
    }

    @Test
    public void testFindById() {
        Long playlistId = 1L;
        Playlists playlist = new Playlists("Playlist 1", playlistId);

        Mockito.when(playlistsRepository.findById(playlistId)).thenReturn(Optional.of(playlist));

        Optional<Playlists> result = playlistService.findById(playlistId);

        assertTrue(result.isPresent());
        assertEquals(playlist, result.get());
    }

    @Test
    public void testFindByIdNotFound() {
        Long playlistId = 1L;

        Mockito.when(playlistsRepository.findById(playlistId)).thenReturn(Optional.empty());

        Optional<Playlists> result = playlistService.findById(playlistId);

        assertFalse(result.isPresent());
    }

    @Test
    public void testSave() {
        Playlists playlist = new Playlists("New Playlist", 1L);

        Mockito.when(playlistsRepository.save(playlist)).thenReturn(playlist);

        Playlists savedPlaylist = playlistService.save(playlist);

        assertEquals(playlist, savedPlaylist);
    }

    @Test
    public void testUpdate() {
        Long playlistId = 1L;
        Playlists existingPlaylist = new Playlists("Existing Playlist", playlistId);
        Playlists updatedPlaylist = new Playlists("Updated Playlist", playlistId);

        Mockito.when(playlistsRepository.findById(playlistId)).thenReturn(Optional.of(existingPlaylist));
        Mockito.when(playlistsRepository.save(updatedPlaylist)).thenReturn(updatedPlaylist);

        Playlists result = playlistService.update(updatedPlaylist);

        assertEquals(updatedPlaylist, result);
    }

    @Test
    public void testUpdateNotFound() {
        Long playlistId = 1L;
        Playlists updatedPlaylist = new Playlists("Updated Playlist", playlistId);

        Mockito.when(playlistsRepository.findById(playlistId)).thenReturn(Optional.empty());

        Playlists result = playlistService.update(updatedPlaylist);

        assertNull(result);
    }

    @Test
    public void testDeleteById() {
        Long playlistId = 1L;

        Mockito.when(playlistsRepository.existsById(playlistId)).thenReturn(true);

        boolean deleted = playlistService.deleteById(playlistId);

        assertTrue(deleted);
    }

    @Test
    public void testDeleteByIdNotFound() {
        Long playlistId = 1L;

        Mockito.when(playlistsRepository.existsById(playlistId)).thenReturn(false);

        boolean deleted = playlistService.deleteById(playlistId);

        assertFalse(deleted);
    }
}
