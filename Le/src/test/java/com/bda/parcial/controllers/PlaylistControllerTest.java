package com.bda.parcial.controllers;
import com.bda.parcial.entities.dto.PlaylistDto;
import com.bda.parcial.request.PlaylistCreationRequest;
import com.bda.parcial.services.playlist.PlaylistService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class PlaylistControllerTest {

    @InjectMocks
    private PlaylistController yourController;

    @Mock
    private PlaylistService playlistService;

    public PlaylistControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddPlaylistByParams() {

        PlaylistCreationRequest playlistCreationRequest = new PlaylistCreationRequest();
        playlistCreationRequest.setName("Mi Playlist");
        playlistCreationRequest.setCustomerId(1L);
        playlistCreationRequest.setGenreId(2L);
        playlistCreationRequest.setComposerFilter("Compositor");
        playlistCreationRequest.setDuracionMaxima(3600);

        ResponseEntity<PlaylistDto> response = yourController.addPlaylistByParams(playlistCreationRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        verify(playlistService).addPlaylistByParams("Mi Playlist", 1L, 2L, "Compositor", 3600);
    }
}
