package utn.frc.parcial1.parcialback.services.trackService;

import org.springframework.stereotype.Service;
import utn.frc.parcial1.parcialback.entities.InvoiceItems;
import utn.frc.parcial1.parcialback.entities.Invoices;
import utn.frc.parcial1.parcialback.entities.Tracks;
import utn.frc.parcial1.parcialback.entities.dto.TrackDto;
import utn.frc.parcial1.parcialback.entities.dto.tranformation.TrackMapper.TrackDtoMapper;
import utn.frc.parcial1.parcialback.entities.dto.tranformation.TrackMapper.TrackMapper;
import utn.frc.parcial1.parcialback.repositories.InvoiceItemsRepository;
import utn.frc.parcial1.parcialback.repositories.InvoicesRepository;
import utn.frc.parcial1.parcialback.repositories.TracksRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrackServiceImp implements TrackService{
    private final TracksRepository tracksRepository;
    private final TrackDtoMapper trackDtoMapper;
    private final TrackMapper trackMapper;
    private final InvoiceItemsRepository invoiceItemsRepository;
    private final InvoicesRepository invoicesRepository;

    public TrackServiceImp(TracksRepository tracksRepository,
                              TrackDtoMapper trackDtoMapper,
                              TrackMapper trackMapper, InvoicesRepository invoicesRepository
                                ,InvoiceItemsRepository invoiceItemsRepository) {
        this.tracksRepository = tracksRepository;
        this.trackDtoMapper = trackDtoMapper;
        this.trackMapper = trackMapper;
        this.invoiceItemsRepository= invoiceItemsRepository;
        this.invoicesRepository=invoicesRepository;
    }

    @Override
    public void add(TrackDto entity) {
        Tracks tracks = Optional.of(entity).map(trackMapper).get();
        tracksRepository.save(tracks);
    }

    @Override
    public TrackDto getById(Long id){
        Optional<Tracks> tracks = tracksRepository.findById(id);
        return tracks
                .map(trackDtoMapper)
                .orElseThrow();
    }
    @Override
    public List<TrackDto> getAll() {
        List<Tracks> values = tracksRepository.findAll();
        return values
                .stream()
                .map(trackDtoMapper)
                .toList();
    }
    @Override
    public TrackDto delete(Long id){
        Optional<Tracks> optionalTracks = tracksRepository
                .findById(id);
        optionalTracks.ifPresent(tracksRepository::delete);
        return optionalTracks
                .map(trackDtoMapper)
                .orElseThrow();
    }

    @Override
    public void update(TrackDto entity, Long id) {
        Optional<Tracks> tracks = tracksRepository.findById(id);
        Tracks trackExistente = tracks.get();
        trackExistente.setName(entity.getName());
        trackExistente.setAlbumId(entity.getAlbumId());
        trackExistente.setMediaTypeId(entity.getMediaTypeId());
        trackExistente.setGenreId(entity.getGenreId());
        trackExistente.setMilliseconds(entity.getMillisecond());
        trackExistente.setUnitPrice(entity.getUnitPrice());
        tracksRepository.save(trackExistente);
    }
    /*@Override
    public List<TrackDto> getTracksByCustomerGenres(Long customerId, Long genreId) {
        List<Invoices> customerInvoices = invoicesRepository.findByInvoiceCustomerId(customerId);

        List<Long> invoiceIds = new ArrayList<>();
        for (Invoices invoice : customerInvoices) {
            invoiceIds.add(invoice.getInvoiceId());
        }

        List<InvoiceItems> invoiceItems = invoiceItemsRepository.findByInvoiceIdIn(invoiceIds);

        List<Long> trackIds = new ArrayList<>();
        for (InvoiceItems item : invoiceItems) {
            List<Tracks> tracks = item.getTracks();
            for (Tracks track : tracks) {
                trackIds.add(track.getTrackId());
            }
        }

        List<Tracks> tracks = tracksRepository.findByIdIn(trackIds);

        List<Tracks> tracksByGenre = new ArrayList<>();
        for (Tracks track : tracks) {
            if (Objects.equals(track.getGenreId(), genreId)) {
                tracksByGenre.add(track);
            }
        }

        return tracksByGenre.stream()
                .map(trackDtoMapper)
                .collect(Collectors.toList()); }*/
    }


