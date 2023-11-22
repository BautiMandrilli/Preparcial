package utn.frc.parcial1.parcialback.entities.dto.tranformation.TrackMapper;

import org.springframework.stereotype.Service;
import utn.frc.parcial1.parcialback.entities.Tracks;
import utn.frc.parcial1.parcialback.entities.dto.TrackDto;

import java.util.function.Function;

@Service
public class TrackMapper implements Function<TrackDto, Tracks> {

    @Override
    public Tracks apply(TrackDto trackDto){
        return new Tracks(trackDto.getTrackId(),trackDto.getName(),trackDto.getAlbumId(),trackDto.getMediaTypeId(),
                trackDto.getGenreId(),trackDto.getMillisecond(),trackDto.getUnitPrice(),
                trackDto.getPlaylists(),trackDto.getInvoiceLineId());
    }
}
