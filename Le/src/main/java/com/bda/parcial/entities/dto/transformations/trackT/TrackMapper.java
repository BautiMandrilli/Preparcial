package com.bda.parcial.entities.dto.transformations.trackT;

import com.bda.parcial.entities.Track;
import com.bda.parcial.entities.dto.TrackDto;
import org.springframework.stereotype.Service;

import java.util.function.Function;


@Service
public class TrackMapper implements Function<TrackDto, Track> {
    @Override
    public Track apply(TrackDto trackDto) {
        return new Track(trackDto.getId(),
                trackDto.getName(),
                trackDto.getComposer(),
                trackDto.getMilliSeconds());
    }
}
