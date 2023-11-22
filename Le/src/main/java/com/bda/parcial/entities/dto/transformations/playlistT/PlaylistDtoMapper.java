package com.bda.parcial.entities.dto.transformations.playlistT;

import com.bda.parcial.entities.Playlist;
import com.bda.parcial.entities.dto.PlaylistDto;
import org.springframework.stereotype.Service;

import java.util.function.Function;


@Service
public class PlaylistDtoMapper implements Function<Playlist, PlaylistDto> {
    @Override
    public PlaylistDto apply(Playlist playlist) {
        return new PlaylistDto(playlist.getId(), playlist.getName(), playlist.getTracks());
    }
}
