package utn.frc.parcial1.parcialback.services.playlistService;

import org.springframework.stereotype.Service;
import utn.frc.parcial1.parcialback.entities.Playlist;
import utn.frc.parcial1.parcialback.entities.Tracks;
import utn.frc.parcial1.parcialback.entities.dto.PlaylistDto;
import utn.frc.parcial1.parcialback.entities.dto.TrackDto;
import utn.frc.parcial1.parcialback.entities.dto.tranformation.PlaylistMapper.PlaylistDtoMapper;
import utn.frc.parcial1.parcialback.entities.dto.tranformation.PlaylistMapper.PlaylistMapper;
import utn.frc.parcial1.parcialback.entities.dto.tranformation.TrackMapper.TrackDtoMapper;
import utn.frc.parcial1.parcialback.entities.dto.tranformation.TrackMapper.TrackMapper;
import utn.frc.parcial1.parcialback.repositories.PlaylistRepository;
import utn.frc.parcial1.parcialback.repositories.TracksRepository;
import utn.frc.parcial1.parcialback.services.trackService.TrackService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlaylistServiceImp implements PlaylistService {
    private final PlaylistRepository playlistRepository;
    private final PlaylistDtoMapper playlistDtoMapper;
    private final PlaylistMapper playlistMapper;
    private final TrackService trackService;
    public PlaylistServiceImp(PlaylistRepository playlistRepository,
                              PlaylistDtoMapper playlistDtoMapper,
                              PlaylistMapper playlistMapper,TrackService trackService) {
        this.playlistRepository = playlistRepository;
        this.playlistDtoMapper = playlistDtoMapper;
        this.playlistMapper = playlistMapper;
        this.trackService = trackService;
    }

    @Override
    public void add(PlaylistDto entity) {
        Playlist playlist = Optional.of(entity).map(playlistMapper).get();
        playlistRepository.save(playlist);
    }

    @Override
    public PlaylistDto getById(Long id){
        Optional<Playlist> playlist = playlistRepository.findById(id);
        return playlist
                .map(playlistDtoMapper)
                .orElseThrow();
    }
    @Override
    public List<PlaylistDto> getAll() {
        List<Playlist> values = playlistRepository.findAll();
        return values
                .stream()
                .map(playlistDtoMapper)
                .toList();
    }
    @Override
    public PlaylistDto delete(Long id){
        Optional<Playlist> optionalPlaylist = playlistRepository
                .findById(id);
        optionalPlaylist.ifPresent(playlistRepository::delete);
        return optionalPlaylist
                .map(playlistDtoMapper)
                .orElseThrow();
    }

    @Override
    public void update(PlaylistDto entity, Long id) {
        Optional<Playlist> playlist = playlistRepository.findById(id);
        Playlist playlistExistente = playlist.get();
        playlistExistente.setName(entity.getName());
        playlistRepository.save(playlistExistente);
    }

    /*@Override
    public void addPlaylis(PlaylistDto entity, String name, int composerFilter, Long customerId, Long genreId) {
        List<TrackDto> trackDtoList = trackService.getTracksByCustomerGenres(customerId, genreId);
        List<Tracks> tracks = trackDtoList.stream()
                .map(Tracks::getTrackId)
                .collect(Collectors.toList());
    }*/
}
