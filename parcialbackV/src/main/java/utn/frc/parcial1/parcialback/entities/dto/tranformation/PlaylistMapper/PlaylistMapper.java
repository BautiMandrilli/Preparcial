package utn.frc.parcial1.parcialback.entities.dto.tranformation.PlaylistMapper;

import org.springframework.stereotype.Service;
import utn.frc.parcial1.parcialback.entities.Playlist;
import utn.frc.parcial1.parcialback.entities.dto.PlaylistDto;

import java.util.function.Function;

@Service
public class PlaylistMapper implements Function<PlaylistDto, Playlist> {
    @Override
    public Playlist apply(PlaylistDto playlistDto){
        return new Playlist(playlistDto.getPlayListId(),playlistDto.getName(),playlistDto.getTrackId());
    }
}
