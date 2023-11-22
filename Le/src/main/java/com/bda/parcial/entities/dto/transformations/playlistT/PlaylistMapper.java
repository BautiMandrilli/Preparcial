package com.bda.parcial.entities.dto.transformations.playlistT;

import com.bda.parcial.entities.Playlist;
import com.bda.parcial.entities.dto.PlaylistDto;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class PlaylistMapper implements Function<PlaylistDto, Playlist> {
    @Override
    public Playlist apply(PlaylistDto playlistDto) {
        return new Playlist(playlistDto.getId(), playlistDto.getName(), playlistDto.getTracks());
    }
}
