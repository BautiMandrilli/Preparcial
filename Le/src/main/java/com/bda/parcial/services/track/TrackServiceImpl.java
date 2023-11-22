package com.bda.parcial.services.track;


import com.bda.parcial.entities.Invoice;
import com.bda.parcial.entities.InvoiceItem;
import com.bda.parcial.entities.Track;
import com.bda.parcial.entities.dto.TrackDto;
import com.bda.parcial.entities.dto.transformations.trackT.TrackDtoMapper;
import com.bda.parcial.entities.dto.transformations.trackT.TrackMapper;
import com.bda.parcial.repositories.InvoiceItemRepository;
import com.bda.parcial.repositories.InvoiceRepository;
import com.bda.parcial.repositories.TrackRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrackServiceImpl implements TrackService {

    private final TrackRepository trackRepository;

    private final TrackDtoMapper trackDtoMapper;

    private final TrackMapper trackMapper;

    private final InvoiceRepository invoiceRepository;

    private final InvoiceItemRepository invoiceItemRepository;

    public TrackServiceImpl(TrackRepository trackRepository, TrackDtoMapper trackDtoMapper, TrackMapper trackMapper, InvoiceRepository invoiceRepository, InvoiceItemRepository invoiceItemRepository) {
        this.trackRepository = trackRepository;
        this.trackDtoMapper = trackDtoMapper;
        this.trackMapper = trackMapper;
        this.invoiceRepository = invoiceRepository;
        this.invoiceItemRepository = invoiceItemRepository;
    }

    @Override
    public void add(TrackDto entity) {
        Track trackNuevo = trackMapper.apply(entity);
        trackRepository.save(trackNuevo);
    }

    @Override
    public TrackDto getById(Long id) {
        Optional<Track> track = trackRepository.findById(id);
        return track.map(trackDtoMapper).orElseThrow();
    }

    @Override
    public List<TrackDto> getAll() {
        List<Track> values = trackRepository.findAll();
        return values.stream().map(trackDtoMapper).toList();
    }


    @Override
    public TrackDto delete(Long id) {
        Optional<Track> track = trackRepository.findById(id);
        track.ifPresent(trackRepository::delete);
        return track.map(trackDtoMapper).orElseThrow();
    }

    @Override
    public void update(TrackDto entity, Long id) {
        Optional<Track> track = trackRepository.findById(id);
        Track trackExistente = track.get();

        trackExistente.setName(entity.getName());
        trackExistente.setComposer(entity.getComposer());
        trackExistente.setMilliSeconds(entity.getMilliSeconds());

        trackRepository.save(trackExistente);
    }

    public List<TrackDto> getTracksByCustomerId(Long customerId) {
        List<Invoice> customerInvoices = invoiceRepository.findByCustomerId(customerId);

        List<Long> invoiceIds = customerInvoices.stream()
                .map(Invoice::getCustomerId)
                .collect(Collectors.toList());

        List<InvoiceItem> invoiceItems = invoiceItemRepository.findByInvoiceIdIn(invoiceIds);

        List<Long> trackIds = invoiceItems.stream()
                .map(InvoiceItem::getTrackId)
                .collect(Collectors.toList());

        List<Track> tracks = trackRepository.findByIdIn(trackIds);

        return tracks.stream().map(trackDtoMapper).collect(Collectors.toList());
    }

    @Override
    public List<TrackDto> getTracksByCustomerIdAndGenreId(Long customerId, Long genreId) {
        List<TrackDto> tracks = getTracksByCustomerId(customerId);

        tracks.stream().map(trackMapper).toList();

        List<Track> tracksFiltrados = trackRepository.findTracksByGenreId(genreId);

        return tracksFiltrados.stream().map(trackDtoMapper).toList();

    }

    @Override
    public List<TrackDto> getTracksByGenreId(Long genreId) {
        List<Track> tracks = trackRepository.findTracksByGenreId(genreId);

        return tracks.stream().map(trackDtoMapper).collect(Collectors.toList());
    }

}
