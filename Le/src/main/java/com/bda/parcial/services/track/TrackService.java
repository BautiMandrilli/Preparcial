package com.bda.parcial.services.track;

import com.bda.parcial.entities.dto.TrackDto;
import com.bda.parcial.services.Service;

import java.util.List;

public interface TrackService extends Service<TrackDto, Long> {
    List<TrackDto> getTracksByCustomerId(Long idCustomer);

    List<TrackDto> getTracksByCustomerIdAndGenreId(Long idCustomer, Long genreId);

    List<TrackDto> getTracksByGenreId(Long genreId);

}
