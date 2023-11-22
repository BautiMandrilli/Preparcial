package ar.edu.utn.frc.backend.services;
import ar.edu.utn.frc.backend.models.Invoices;
import ar.edu.utn.frc.backend.repositories.InvoicesRepository;
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

public class InvoiceServiceTest {

    @InjectMocks
    private InvoiceService invoiceService;

    @Mock
    private InvoicesRepository invoiceRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAll() {
        List<Invoices> invoices = new ArrayList<>();
        invoices.add(new Invoices(1L, "2023-10-26", "Billing Address 1", "Billing City 1", "Billing State 1", "Billing Country 1", "12345", 100.0, 1L));
        invoices.add(new Invoices(2L, "2023-10-27", "Billing Address 2", "Billing City 2", "Billing State 2", "Billing Country 2", "54321", 200.0, 2L));

        Mockito.when(invoiceRepository.findAll()).thenReturn(invoices);

        List<Invoices> result = invoiceService.findAll();

        assertEquals(2, result.size());
    }

    @Test
    public void testFindById() {
        Long invoiceId = 1L;
        Invoices invoice = new Invoices(1L, "2023-10-26", "Billing Address 1", "Billing City 1", "Billing State 1", "Billing Country 1", "12345", 100.0, invoiceId);

        Mockito.when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(invoice));

        Optional<Invoices> result = invoiceService.findById(invoiceId);

        assertTrue(result.isPresent());
        assertEquals(invoice, result.get());
    }

    @Test
    public void testFindByIdNotFound() {
        Long invoiceId = 1L;

        Mockito.when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.empty());

        Optional<Invoices> result = invoiceService.findById(invoiceId);

        assertFalse(result.isPresent());
    }

    @Test
    public void testSave() {
        Invoices invoice = new Invoices(1L, "2023-10-26", "New Billing Address", "New Billing City", "New Billing State", "New Billing Country", "54321", 200.0, 1L);

        Mockito.when(invoiceRepository.save(invoice)).thenReturn(invoice);

        Invoices savedInvoice = invoiceService.save(invoice);

        assertEquals(invoice, savedInvoice);
    }

    @Test
    public void testUpdate() {
        Long invoiceId = 1L;
        Invoices existingInvoice = new Invoices(1L, "2023-10-26", "Billing Address 1", "Billing City 1", "Billing State 1", "Billing Country 1", "12345", 100.0, invoiceId);
        Invoices updatedInvoice = new Invoices(2L, "2023-10-27", "Updated Billing Address", "Updated Billing City", "Updated Billing State", "Updated Billing Country", "54321", 200.0, invoiceId);

        Mockito.when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(existingInvoice));
        Mockito.when(invoiceRepository.save(updatedInvoice)).thenReturn(updatedInvoice);

        Invoices result = invoiceService.update(updatedInvoice);

        assertEquals(updatedInvoice, result);
    }

    @Test
    public void testUpdateNotFound() {
        Long invoiceId = 1L;
        Invoices updatedInvoice = new Invoices(2L, "2023-10-27", "Updated Billing Address", "Updated Billing City", "Updated Billing State", "Updated Billing Country", "54321", 200.0, invoiceId);

        Mockito.when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.empty());

        Invoices result = invoiceService.update(updatedInvoice);

        assertNull(result);
    }

    @Test
    public void testDeleteById() {
        Long invoiceId = 1L;

        Mockito.when(invoiceRepository.existsById(invoiceId)).thenReturn(true);

        boolean deleted = invoiceService.deleteById(invoiceId);

        assertTrue(deleted);
    }

    @Test
    public void testDeleteByIdNotFound() {
        Long invoiceId = 1L;

        Mockito.when(invoiceRepository.existsById(invoiceId)).thenReturn(false);

        boolean deleted = invoiceService.deleteById(invoiceId);

        assertFalse(deleted);
    }

    @Test
    public void testFindByCustomerId() {
        // Preparación de datos para el test
        Long customerId = 1L;
        List<Invoices> invoices = new ArrayList<>();
        invoices.add(new Invoices(1L, "2023-10-26", "Billing Address 1", "Billing City 1", "Billing State 1", "Billing Country 1", "12345", 100.0, 1L));
        invoices.add(new Invoices(2L, "2023-10-27", "Billing Address 2", "Billing City 2", "Billing State 2", "Billing Country 2", "54321", 200.0, 1L));

        // Configuración de los mocks
        Mockito.when(invoiceRepository.findByCustomerId(customerId)).thenReturn(invoices);

        // Llamada al método a probar
        List<Invoices> result = invoiceService.findByCustomerId(customerId);

        // Verificación de resultados
        assertEquals(2, result.size());
    }
}
