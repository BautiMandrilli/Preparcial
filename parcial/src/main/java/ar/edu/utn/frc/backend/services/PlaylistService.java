package ar.edu.utn.frc.backend.services;

import ar.edu.utn.frc.backend.models.*;
import ar.edu.utn.frc.backend.models.dto.TracksDto;
import ar.edu.utn.frc.backend.repositories.PlaylistsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class PlaylistService {

    @Autowired
    private PlaylistService playlistService;

    @Autowired
    private PlaylistTrackService playlistTrackService;

    @Autowired
    private PlaylistsRepository playlistsRepository;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private InvoiceItemService invoiceItemService;

    @Autowired
    private TrackService trackService;

    public List<Playlists> findAll() {
        return playlistsRepository.findAll();
    }

    public Optional<Playlists> findById(Long id) {
        return playlistsRepository.findById(id);
    }

    public Playlists save(Playlists playlist) {
        return playlistsRepository.save(playlist);
    }

    public Playlists update(Playlists playlist) {
        Long id = playlist.getPlaylistId();
        Optional<Playlists> existingPlaylist = playlistsRepository.findById(id);
        if (existingPlaylist.isPresent()) {
            return playlistsRepository.save(playlist);
        } else {
            return null;
        }
    }

    public boolean deleteById(Long id) {
        if (playlistsRepository.existsById(id)) {
            playlistsRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Playlists createPlaylist(
            String playlistName,
            Long customerId,
            Long genreId,
            String composerFilter,
            Long maxDuration
    ) {
        List<Invoices> invoices = invoiceService.findByCustomerId(customerId);
        List<InvoiceItems> invoiceItems = invoiceItemService.findInvoiceItemsByInvoices(invoices);

        List<TracksDto> matchingTracks = getMatchingTracks(
                invoiceItems,
                genreId,
                composerFilter,
                maxDuration
        );

        if (matchingTracks.isEmpty()) {
            return null;
        }

        Collections.shuffle(matchingTracks);
        List<TracksDto> tracksToAdd = new ArrayList<>();
        long currentDuration = 0;

        Playlists newPlaylist = new Playlists();
        newPlaylist.setName(playlistName);
        newPlaylist = playlistService.save(newPlaylist);

        for (TracksDto track : matchingTracks) {
            if (currentDuration + track.getSegundos() <= maxDuration) {

                PlaylistTrack newPlaylistTrack = new PlaylistTrack();
                newPlaylistTrack.setPlaylistId(newPlaylist.getPlaylistId());
                newPlaylistTrack.setTrackId(track.getTrackId());
                newPlaylistTrack.save(newPlaylistTrack);
                currentDuration += track.getSegundos();
            } else {
                break;
            }
        }

        return newPlaylist;
    }

    private List<TracksDto> getMatchingTracks(
            List<InvoiceItems> invoiceItems,
            Long genreId,
            String composerFilter,
            Long maxDuration
    ) {
        List<Long> trackIds = new ArrayList<>();
        for (InvoiceItems invoiceItem : invoiceItems) {
            Optional<Tracks> track = trackService.findById(invoiceItem.getTrackId());
            if (track.isPresent() &&
                    track.get().getGenreId().equals(genreId) &&
                    track.get().getComposer().contains(composerFilter)) {
                trackIds.add(track.get().getTrackId());
            }
        }

        List<TracksDto> matchingTracks = new ArrayList<>();
        if (!trackIds.isEmpty()) {
            List<Tracks> allTracks = trackService.findAll();
            for (Tracks track : allTracks) {
                if (trackIds.contains(track.getTrackId())) {
                    TracksDto trackDto = trackService.convertToDto(track);
                    matchingTracks.add(trackDto);
                }
            }
        }
        return matchingTracks;
    }
}
