package com.bda.parcial.entities.dto.transformations.trackT;

import com.bda.parcial.entities.Track;
import com.bda.parcial.entities.dto.TrackDto;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class TrackDtoMapper implements Function<Track, TrackDto> {

    @Override
    public TrackDto apply(Track track) {
        return new TrackDto(track.getId(),
                track.getName(),
                track.getComposer(),
                track.getMilliSeconds());
    }
}
