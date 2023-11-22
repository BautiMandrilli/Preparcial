package ar.edu.utn.frc.backend.services;

import ar.edu.utn.frc.backend.models.InvoiceItems;
import ar.edu.utn.frc.backend.models.Tracks;
import ar.edu.utn.frc.backend.models.dto.TracksDto;
import ar.edu.utn.frc.backend.repositories.TracksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrackService {
    @Autowired
    private TracksRepository tracksRepository;

    public List<Tracks> findAll() {
        return tracksRepository.findAll();
    }

    public Optional<Tracks> findById(Long id) {
        return tracksRepository.findById(id);
    }

    public Tracks save(Tracks track) {
        return tracksRepository.save(track);
    }

    public Tracks update(Tracks track) {
        Long id = track.getTrackId();
        Optional<Tracks> existingTrack = tracksRepository.findById(id);
        if (existingTrack.isPresent()) {
            return tracksRepository.save(track);
        } else {
            return null;
        }
    }

    public boolean deleteById(Long id) {
        if (tracksRepository.existsById(id)) {
            tracksRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public List<TracksDto> getTracksByGenreAndCustomer(List<InvoiceItems> invoicesItems, Long genreId) {
        List<Long> trackIds = invoicesItems.stream()
                .filter(invoiceItem -> tracksRepository.findByTrackIdAndGenreId(invoiceItem.getTrackId(), genreId).isPresent())
                .map(InvoiceItems::getTrackId)
                .distinct()
                .collect(Collectors.toList());
        List<Tracks> tracks = tracksRepository.findByTrackIdIn(trackIds);
        List<TracksDto> tracksDtos = tracks.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return tracksDtos;
    }

    public TracksDto convertToDto(Tracks track) {
        TracksDto dto = new TracksDto();
        dto.setTrackId(track.getTrackId());
        dto.setName(track.getName());
        dto.setComposer(track.getComposer());
        dto.setSegundos(track.getMilliseconds() / 1000);
        return dto;
    }

}
