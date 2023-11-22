package utn.frc.parcial1.parcialback.entities.dto.tranformation.PlaylistMapper;

import org.springframework.stereotype.Service;
import utn.frc.parcial1.parcialback.entities.Playlist;
import utn.frc.parcial1.parcialback.entities.dto.PlaylistDto;

import java.util.function.Function;

@Service
public class PlaylistDtoMapper implements Function<Playlist, PlaylistDto> {
    @Override
    public PlaylistDto apply(Playlist playlist){

        return new PlaylistDto(playlist.getPlaylistId(),playlist.getName(),playlist.getTracks());
    }
}
