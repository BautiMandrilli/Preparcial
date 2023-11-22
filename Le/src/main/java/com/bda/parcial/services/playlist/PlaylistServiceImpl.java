package com.bda.parcial.services.playlist;

import com.bda.parcial.entities.Playlist;
import com.bda.parcial.entities.Track;
import com.bda.parcial.entities.dto.PlaylistDto;
import com.bda.parcial.entities.dto.TrackDto;
import com.bda.parcial.entities.dto.transformations.playlistT.PlaylistDtoMapper;
import com.bda.parcial.entities.dto.transformations.playlistT.PlaylistMapper;
import com.bda.parcial.entities.dto.transformations.trackT.TrackMapper;
import com.bda.parcial.repositories.PlaylistRepository;
import com.bda.parcial.services.track.TrackServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class PlaylistServiceImpl implements PlaylistService{

    private final PlaylistRepository playlistRepository;

    private final PlaylistDtoMapper playlistDtoMapper;

    private final PlaylistMapper playlistMapper;

    private final TrackServiceImpl trackService;

    private final TrackMapper trackMapper;

    public PlaylistServiceImpl(PlaylistRepository playlistRepository, PlaylistDtoMapper playlistDtoMapper, PlaylistMapper playlistMapper, TrackServiceImpl trackService, TrackMapper trackMapper) {
        this.playlistRepository = playlistRepository;
        this.playlistDtoMapper = playlistDtoMapper;
        this.playlistMapper = playlistMapper;
        this.trackService = trackService;
        this.trackMapper = trackMapper;
    }


    @Override
    public void addPlaylistByParams(String name, Long customerId, Long genreId, String composer_filter, int duracionMaxima) {
        List<TrackDto> tracksFiltradosPorCustomerYGenero = trackService.getTracksByCustomerIdAndGenreId(customerId, genreId);
        List<TrackDto> tracksFiltradosPorComposerDto = new ArrayList<>();
        int duracionTotal = 0;
        for (TrackDto track : tracksFiltradosPorCustomerYGenero) {
            if (track.getComposer() != null && track.getComposer().contains(composer_filter)) {
                tracksFiltradosPorComposerDto.add(track);
                duracionTotal += track.getMilliSeconds();
            }
        }
        List<Track> tracksFiltradosPorComposer = new ArrayList<>();
        if (duracionTotal != 0 || duracionTotal <= duracionMaxima || !tracksFiltradosPorComposerDto.isEmpty()) {
            Playlist playlistNueva = new Playlist();
            playlistNueva.setName(name);
            for (TrackDto trackDto : tracksFiltradosPorComposerDto) {
                Track track = trackMapper.apply(trackDto);
                tracksFiltradosPorComposer.add(track);

            }
            playlistNueva.setTracks(tracksFiltradosPorComposer);
            playlistRepository.save(playlistNueva);

        }
    }

    @Override
    public void add(PlaylistDto entity) {
        Playlist playlistNueva = playlistMapper.apply(entity);
        playlistRepository.save(playlistNueva);
    }

    @Override
    public PlaylistDto getById(Long id) {
        Optional<Playlist> playlist = playlistRepository.findById(id);
        return playlist.map(playlistDtoMapper).orElseThrow();
    }

    @Override
    public List<PlaylistDto> getAll() {
        List<Playlist> values = playlistRepository.findAll();
        return values.stream().map(playlistDtoMapper).toList();
    }

    @Override
    public PlaylistDto delete(Long id) {
        Optional<Playlist> playlist = playlistRepository.findById(id);
        playlist.ifPresent(playlistRepository::delete);
        return playlist.map(playlistDtoMapper).orElseThrow();
    }

    @Override
    public void update(PlaylistDto entity, Long id) {

        Optional<Playlist> playlist = playlistRepository.findById(id);
        Playlist playlistExistente = playlist.get();

        playlistExistente.setName(entity.getName());


        playlistRepository.save(playlistExistente);

    }
}
