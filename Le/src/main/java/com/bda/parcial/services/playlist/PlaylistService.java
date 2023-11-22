package com.bda.parcial.services.playlist;

import com.bda.parcial.entities.dto.PlaylistDto;
import com.bda.parcial.services.Service;

public interface PlaylistService extends Service<PlaylistDto, Long> {

    void addPlaylistByParams(String name, Long customerId,
                             Long genreId, String composer_filter, int duracionMaxima);
}
