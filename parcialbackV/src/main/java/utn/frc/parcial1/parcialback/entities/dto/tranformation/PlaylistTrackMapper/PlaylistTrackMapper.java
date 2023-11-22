package utn.frc.parcial1.parcialback.entities.dto.tranformation.PlaylistTrackMapper;

import org.springframework.stereotype.Service;

import utn.frc.parcial1.parcialback.entities.PlaylistTrack;
import utn.frc.parcial1.parcialback.entities.dto.PlaylistDto;
import utn.frc.parcial1.parcialback.entities.dto.PlaylistTrackDto;

import java.util.function.Function;
@Service
public class PlaylistTrackMapper implements Function<PlaylistTrackDto,PlaylistTrack>{

    @Override
    public PlaylistTrack apply(PlaylistTrackDto playlistTrackDto) {
        return new PlaylistTrack(playlistTrackDto.getPlaylistId(),playlistTrackDto.getTrackId());
    }
}
