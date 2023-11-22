package utn.frc.parcial1.parcialback.entities.dto.tranformation.PlaylistTrackMapper;

import org.springframework.stereotype.Service;
import utn.frc.parcial1.parcialback.entities.PlaylistTrack;
import utn.frc.parcial1.parcialback.entities.dto.PlaylistTrackDto;

import java.util.function.Function;

@Service
public class PlaylistTrackDtoMapper implements Function<PlaylistTrack, PlaylistTrackDto> {

    @Override
    public PlaylistTrackDto apply(PlaylistTrack playlistTrack){
        return new PlaylistTrackDto(playlistTrack.getPlaylistId(),playlistTrack.getTrackId());
    }
}
