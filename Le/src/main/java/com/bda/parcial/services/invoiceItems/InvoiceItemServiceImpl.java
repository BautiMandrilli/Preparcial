package com.bda.parcial.services.invoiceItems;


import com.bda.parcial.entities.InvoiceItem;
import com.bda.parcial.entities.dto.InvoiceItemDto;
import com.bda.parcial.entities.dto.transformations.invoiceItemsT.InvoiceItemDtoMapper;
import com.bda.parcial.entities.dto.transformations.invoiceItemsT.InvoiceItemMapper;
import com.bda.parcial.repositories.InvoiceItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceItemServiceImpl implements InvoiceItemService {

    private final InvoiceItemRepository invoiceItemRepository;

    private final InvoiceItemMapper invoiceItemMapper;

    private final InvoiceItemDtoMapper invoiceItemDtoMapper;

    public InvoiceItemServiceImpl(InvoiceItemRepository invoiceItemRepository, InvoiceItemDtoMapper invoiceItemDtoMapper, InvoiceItemMapper invoiceItemMapper) {
        this.invoiceItemRepository = invoiceItemRepository;
        this.invoiceItemMapper = invoiceItemMapper;
        this.invoiceItemDtoMapper = invoiceItemDtoMapper;
    }

    @Override
    public void add(InvoiceItemDto entity) {
        InvoiceItem invoiceItemNuevo = invoiceItemMapper.apply(entity);
        invoiceItemRepository.save(invoiceItemNuevo);
    }

    @Override
    public InvoiceItemDto getById(Long id) {
        Optional<InvoiceItem> invoiceItem = invoiceItemRepository.findById(id);
        return invoiceItem.map(invoiceItemDtoMapper).orElseThrow();
    }

    @Override
    public List<InvoiceItemDto> getAll() {
        List<InvoiceItem> values = invoiceItemRepository.findAll();
        return values.stream().map(invoiceItemDtoMapper).toList();
    }

    @Override
    public InvoiceItemDto delete(Long id) {
        Optional<InvoiceItem> invoiceItem = invoiceItemRepository.findById(id);
        invoiceItem.ifPresent(invoiceItemRepository::delete);
        return invoiceItem.map(invoiceItemDtoMapper).orElseThrow();
    }

    @Override
    public void update(InvoiceItemDto entity, Long id) {

        Optional<InvoiceItem> invoiceItem = invoiceItemRepository.findById(id);
        InvoiceItem invoiceItemExistente = invoiceItem.get();

        invoiceItemExistente.setInvoiceId(entity.getInvoiceId());
        invoiceItemExistente.setTrackId(entity.getTrackId());
        invoiceItemExistente.setQuantity(entity.getQuantity());
        invoiceItemExistente.setUnitPrice(entity.getUnitPrice());

        invoiceItemRepository.save(invoiceItemExistente);
    }
}
