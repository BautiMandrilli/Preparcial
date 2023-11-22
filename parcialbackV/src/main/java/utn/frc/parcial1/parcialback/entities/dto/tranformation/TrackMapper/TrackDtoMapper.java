package utn.frc.parcial1.parcialback.entities.dto.tranformation.TrackMapper;

import org.springframework.stereotype.Service;
import utn.frc.parcial1.parcialback.entities.Tracks;
import utn.frc.parcial1.parcialback.entities.dto.TrackDto;

import java.util.function.Function;

@Service
public class TrackDtoMapper implements Function<Tracks, TrackDto> {
    @Override
    public TrackDto apply(Tracks tracks){
        return new TrackDto(tracks.getTrackId(), tracks.getName(), tracks.getAlbumId(),
                tracks.getMediaTypeId(), tracks.getGenreId(),tracks.getMilliseconds(),
                tracks.getUnitPrice(),tracks.getPlaylists(),tracks.getInvoiceLineId());
    }
}
