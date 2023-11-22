package ar.edu.utn.frc.backend.services;

import ar.edu.utn.frc.backend.models.InvoiceItems;
import ar.edu.utn.frc.backend.models.Tracks;
import ar.edu.utn.frc.backend.models.dto.TracksDto;
import ar.edu.utn.frc.backend.repositories.TracksRepository;
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

public class TrackServiceTest {

    @InjectMocks
    private TrackService trackService;

    @Mock
    private TracksRepository tracksRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAll() {
        List<Tracks> tracks = new ArrayList<>();
        tracks.add(new Tracks("Track 1", 1L, 1L, 1L, "Composer 1", 1000L, 1024L, 9.99, 1L));
        tracks.add(new Tracks("Track 2", 2L, 2L, 2L, "Composer 2", 2000L, 2048L, 7.99, 2L));

        Mockito.when(tracksRepository.findAll()).thenReturn(tracks);

        List<Tracks> result = trackService.findAll();

        assertEquals(2, result.size());
    }

    @Test
    public void testFindById() {
        Long trackId = 1L;
        Tracks track = new Tracks("Track 1", 1L, 1L, 1L, "Composer 1", 1000L, 1024L, 9.99, trackId);

        Mockito.when(tracksRepository.findById(trackId)).thenReturn(Optional.of(track));

        Optional<Tracks> result = trackService.findById(trackId);

        assertTrue(result.isPresent());
        assertEquals(track, result.get());
    }

    @Test
    public void testFindByIdNotFound() {
        Long trackId = 1L;

        Mockito.when(tracksRepository.findById(trackId)).thenReturn(Optional.empty());

        Optional<Tracks> result = trackService.findById(trackId);

        assertFalse(result.isPresent());
    }

    @Test
    public void testSave() {
        Tracks track = new Tracks("New Track", 1L, 1L, 1L, "New Composer", 3000L, 3072L, 8.99, 1L);

        Mockito.when(tracksRepository.save(track)).thenReturn(track);

        Tracks savedTrack = trackService.save(track);

        assertEquals(track, savedTrack);
    }

    @Test
    public void testUpdate() {
        Long trackId = 1L;
        Tracks existingTrack = new Tracks("Track 1", 1L, 1L, 1L, "Composer 1", 1000L, 1024L, 9.99, trackId);
        Tracks updatedTrack = new Tracks("Updated Track", 2L, 2L, 2L, "Updated Composer", 2000L, 2048L, 7.99, trackId);

        Mockito.when(tracksRepository.findById(trackId)).thenReturn(Optional.of(existingTrack));
        Mockito.when(tracksRepository.save(updatedTrack)).thenReturn(updatedTrack);

        Tracks result = trackService.update(updatedTrack);

        assertEquals(updatedTrack, result);
    }

    @Test
    public void testUpdateNotFound() {
        Long trackId = 1L;
        Tracks updatedTrack = new Tracks("Updated Track", 2L, 2L, 2L, "Updated Composer", 2000L, 2048L, 7.99, trackId);

        Mockito.when(tracksRepository.findById(trackId)).thenReturn(Optional.empty());

        Tracks result = trackService.update(updatedTrack);

        assertNull(result);
    }

    @Test
    public void testDeleteById() {
        Long trackId = 1L;

        Mockito.when(tracksRepository.existsById(trackId)).thenReturn(true);

        boolean deleted = trackService.deleteById(trackId);

        assertTrue(deleted);
    }

    @Test
    public void testDeleteByIdNotFound() {
        Long trackId = 1L;

        Mockito.when(tracksRepository.existsById(trackId)).thenReturn(false);

        boolean deleted = trackService.deleteById(trackId);

        assertFalse(deleted);
    }

    @Test
    public void testGetTracksByGenreAndCustomer() {
        // Preparación de datos para el test
        List<InvoiceItems> invoiceItems = new ArrayList<>();
        invoiceItems.add(new InvoiceItems(1L, 1L, 1.0, 1L, 1L));
        invoiceItems.add(new InvoiceItems(2L, 2L, 1.0, 1L, 2L));

        List<Tracks> tracks = new ArrayList<>();
        tracks.add(new Tracks("Track 1", 1L, 1L, 1L, "Composer 1", 1000L, 1024L, 9.99, 1L));
        tracks.add(new Tracks("Track 2", 2L, 2L, 2L, "Composer 2", 2000L, 2048L, 7.99, 2L));

        Long genreId = 1L;

        Mockito.when(tracksRepository.findByTrackIdAndGenreId(1L, genreId)).thenReturn(Optional.of(tracks.get(0)));
        Mockito.when(tracksRepository.findByTrackIdAndGenreId(2L, genreId)).thenReturn(Optional.empty());
        Mockito.when(tracksRepository.findByTrackIdIn(Mockito.anyList())).thenReturn(tracks);

        List<TracksDto> tracksDtos = trackService.getTracksByGenreAndCustomer(invoiceItems, genreId);

        assertEquals(2, tracksDtos.size());
        assertEquals("Track 1", tracksDtos.get(0).getName());
    }
}
