package com.bda.parcial.controllers;
import com.bda.parcial.entities.dto.TrackDto;
import com.bda.parcial.services.track.TrackService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class TrackControllerTest {
    @InjectMocks
    private TrackController trackController;

    @Mock
    private TrackService trackService;

    public TrackControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetTracks() {
        Long customerId = 1L;
        Long genreId = 2L;

        List<TrackDto> trackDtos = Collections.singletonList(new TrackDto());

        when(trackService.getTracksByCustomerIdAndGenreId(customerId, genreId)).thenReturn(trackDtos);

        ResponseEntity<List<TrackDto>> response = trackController.getTracks(customerId, genreId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(trackDtos, response.getBody());
    }
}
