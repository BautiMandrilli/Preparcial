package ar.edu.utn.frc.backend.services;

import ar.edu.utn.frc.backend.models.InvoiceItems;
import ar.edu.utn.frc.backend.models.Invoices;
import ar.edu.utn.frc.backend.repositories.InvoiceItemsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class InvoiceItemServiceTest {

    @InjectMocks
    private InvoiceItemService invoiceItemService;

    @Mock
    private InvoiceItemsRepository invoiceItemsRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAll() {
        List<InvoiceItems> invoiceItems = new ArrayList<>();
        invoiceItems.add(new InvoiceItems(1L, 2L, 9.99, 3, 1L));
        invoiceItems.add(new InvoiceItems(2L, 3L, 7.99, 2, 2L));

        Mockito.when(invoiceItemsRepository.findAll()).thenReturn(invoiceItems);

        List<InvoiceItems> result = invoiceItemService.findAll();

        assertEquals(2, result.size());
    }

    @Test
    public void testFindById() {
        Long invoiceLineId = 1L;
        InvoiceItems invoiceItem = new InvoiceItems(1L, 2L, 9.99, 3, invoiceLineId);

        Mockito.when(invoiceItemsRepository.findById(invoiceLineId)).thenReturn(Optional.of(invoiceItem));

        Optional<InvoiceItems> result = invoiceItemService.findById(invoiceLineId);

        assertTrue(result.isPresent());
        assertEquals(invoiceItem, result.get());
    }

    @Test
    public void testFindByIdNotFound() {
        Long invoiceLineId = 1L;

        Mockito.when(invoiceItemsRepository.findById(invoiceLineId)).thenReturn(Optional.empty());

        Optional<InvoiceItems> result = invoiceItemService.findById(invoiceLineId);

        assertFalse(result.isPresent());
    }

    @Test
    public void testSave() {
        InvoiceItems invoiceItem = new InvoiceItems(1L, 2L, 9.99, 3, 1L);

        Mockito.when(invoiceItemsRepository.save(invoiceItem)).thenReturn(invoiceItem);

        InvoiceItems savedInvoiceItem = invoiceItemService.save(invoiceItem);

        assertEquals(invoiceItem, savedInvoiceItem);
    }

    @Test
    public void testUpdate() {
        Long invoiceLineId = 1L;
        InvoiceItems existingInvoiceItem = new InvoiceItems(1L, 2L, 9.99, 3, invoiceLineId);
        InvoiceItems updatedInvoiceItem = new InvoiceItems(2L, 3L, 7.99, 2, invoiceLineId);

        Mockito.when(invoiceItemsRepository.findById(invoiceLineId)).thenReturn(Optional.of(existingInvoiceItem));
        Mockito.when(invoiceItemsRepository.save(updatedInvoiceItem)).thenReturn(updatedInvoiceItem);

        InvoiceItems result = invoiceItemService.update(updatedInvoiceItem);

        assertEquals(updatedInvoiceItem, result);
    }

    @Test
    public void testUpdateNotFound() {
        Long invoiceLineId = 1L;
        InvoiceItems updatedInvoiceItem = new InvoiceItems(2L, 3L, 7.99, 2, invoiceLineId);

        Mockito.when(invoiceItemsRepository.findById(invoiceLineId)).thenReturn(Optional.empty());

        InvoiceItems result = invoiceItemService.update(updatedInvoiceItem);

        assertNull(result);
    }

    @Test
    public void testDeleteById() {
        Long invoiceLineId = 1L;

        Mockito.when(invoiceItemsRepository.existsById(invoiceLineId)).thenReturn(true);

        boolean deleted = invoiceItemService.deleteById(invoiceLineId);

        assertTrue(deleted);
    }

    @Test
    public void testDeleteByIdNotFound() {
        Long invoiceLineId = 1L;

        Mockito.when(invoiceItemsRepository.existsById(invoiceLineId)).thenReturn(false);

        boolean deleted = invoiceItemService.deleteById(invoiceLineId);

        assertFalse(deleted);
    }

    @Test
    public void testFindInvoiceItemsByInvoices() {
        // Preparación de datos para el test
        List<Invoices> invoicesList = new ArrayList<>();
        invoicesList.add(new Invoices(1L, "2023-10-26", "Address 1", "City 1", "State 1", "Country 1", "PostalCode 1", 9.99, 1L));
        invoicesList.add(new Invoices(2L, "2023-10-27", "Address 2", "City 2", "State 2", "Country 2", "PostalCode 2", 7.99, 2L));

        List<InvoiceItems> invoiceItems = new ArrayList<>();
        invoiceItems.add(new InvoiceItems(1L, 1L, 9.99, 1, 1L));
        invoiceItems.add(new InvoiceItems(2L, 2L, 7.99, 2, 2L));

        Mockito.when(invoiceItemsRepository.findByInvoiceIdIn(Mockito.anyList())).thenReturn(invoiceItems);

        List<InvoiceItems> result = invoiceItemService.findInvoiceItemsByInvoices(invoicesList);

        assertEquals(2, result.size());
    }
}
