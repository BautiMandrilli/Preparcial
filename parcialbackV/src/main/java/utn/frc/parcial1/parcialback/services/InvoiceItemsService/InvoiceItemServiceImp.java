package utn.frc.parcial1.parcialback.services.InvoiceItemsService;

import org.springframework.stereotype.Service;
import utn.frc.parcial1.parcialback.entities.InvoiceItems;
import utn.frc.parcial1.parcialback.entities.dto.InvoiceItemsDto;
import utn.frc.parcial1.parcialback.entities.dto.tranformation.InvoiceItemMapper.InvoiceItemDtoMapper;
import utn.frc.parcial1.parcialback.entities.dto.tranformation.InvoiceItemMapper.InvoiceItemMapper;
import utn.frc.parcial1.parcialback.repositories.InvoiceItemsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceItemServiceImp implements InvoiceItemService {
    private final InvoiceItemsRepository invoiceItemsRepository;
    private final InvoiceItemDtoMapper invoiceItemDtoMapper;
    private final InvoiceItemMapper invoiceItemMapper;

    public InvoiceItemServiceImp(InvoiceItemsRepository invoiceItemsRepository,
                              InvoiceItemDtoMapper invoiceItemDtoMapper,
                              InvoiceItemMapper invoiceItemMapper) {
        this.invoiceItemsRepository = invoiceItemsRepository;
        this.invoiceItemDtoMapper = invoiceItemDtoMapper;
        this.invoiceItemMapper = invoiceItemMapper;
    }

    @Override
    public void add(InvoiceItemsDto entity) {
        InvoiceItems invoiceItems = Optional.of(entity).map(invoiceItemMapper).get();
        invoiceItemsRepository.save(invoiceItems);
    }

    @Override
    public InvoiceItemsDto getById(Long id){
        Optional<InvoiceItems> invoiceItems = invoiceItemsRepository.findById(id);
        return invoiceItems
                .map(invoiceItemDtoMapper)
                .orElseThrow();
    }
    @Override
    public List<InvoiceItemsDto> getAll() {
        List<InvoiceItems> values = invoiceItemsRepository.findAll();
        return values
                .stream()
                .map(invoiceItemDtoMapper)
                .toList();
    }
    @Override
    public InvoiceItemsDto delete(Long id){
        Optional<InvoiceItems> optionalInvoiceItems = invoiceItemsRepository
                .findById(id);
        optionalInvoiceItems.ifPresent(invoiceItemsRepository::delete);
        return optionalInvoiceItems
                .map(invoiceItemDtoMapper)
                .orElseThrow();
    }

    @Override
    public void update(InvoiceItemsDto entity, Long id) {
        Optional<InvoiceItems> invoiceItems = invoiceItemsRepository.findById(id);
        InvoiceItems invoiceItemsExistente = invoiceItems.get();
        invoiceItemsExistente.setInvoiceId(entity.getInvoiceId());
        invoiceItemsExistente.setTrackId(entity.getTrackId());
        invoiceItemsExistente.setUnitPrice(entity.getUnitPrice());
        invoiceItemsExistente.setQuantity(entity.getQuantity());
        invoiceItemsRepository.save(invoiceItemsExistente);
    }
}
