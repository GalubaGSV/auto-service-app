package galuba.autoservice.service.impl;

import galuba.autoservice.model.Maintenance;
import galuba.autoservice.model.Master;
import galuba.autoservice.model.PaymentStatus;
import galuba.autoservice.repository.MaintenancesRepository;
import galuba.autoservice.repository.MasterRepository;
import galuba.autoservice.service.MasterService;
import galuba.autoservice.service.impl.MasterServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MasterServiceImplTest {
    private MasterService masterService;

    @Mock
    private MasterRepository masterRepository;

    @Mock
    private MaintenancesRepository maintenancesRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        masterService = new MasterServiceImpl(masterRepository, maintenancesRepository);
    }

    @Test
    public void testSaveMaster() {
        // Arrange
        Master master = new Master();
        master.setId(1L);
        master.setName("John Doe");

        when(masterRepository.save(any(Master.class))).thenReturn(master);

        // Act
        Master savedMaster = masterService.save(master);

        // Assert
        assertEquals(master, savedMaster);
        verify(masterRepository, times(1)).save(master);
    }

    @Test
    public void testFindMasterById() {
        // Arrange
        Long masterId = 1L;
        Master master = new Master();
        master.setId(masterId);
        master.setName("John Doe");

        when(masterRepository.findById(masterId)).thenReturn(Optional.of(master));

        // Act
        Master foundMaster = masterService.findById(masterId);

        // Assert
        assertEquals(master, foundMaster);
        verify(masterRepository, times(1)).findById(masterId);
    }

    @Test
    public void testCalculateSalary() {
        // Arrange
        Long masterId = 1L;

        Maintenance maintenance1 = new Maintenance();
        maintenance1.setId(1L);
        maintenance1.setPrice(new BigDecimal("100.00"));
        maintenance1.setPaymentStatus(PaymentStatus.NOT_PAID);

        Maintenance maintenance2 = new Maintenance();
        maintenance2.setId(2L);
        maintenance2.setPrice(new BigDecimal("200.00"));
        maintenance2.setPaymentStatus(PaymentStatus.NOT_PAID);

        List<Maintenance> maintenances = new ArrayList<>();
        maintenances.add(maintenance1);
        maintenances.add(maintenance2);

        BigDecimal expectedSalary = new BigDecimal("120.00");

        when(maintenancesRepository.findAllByMaster_Id(masterId)).thenReturn(maintenances);
        when(maintenancesRepository.saveAll(anyIterable())).thenReturn(maintenances);

        // Act
        BigDecimal salary = masterService.calculateSalary(masterId);

        // Assert
        assertEquals(0, expectedSalary.compareTo(salary));
        assertEquals(PaymentStatus.PAID, maintenance1.getPaymentStatus());
        assertEquals(PaymentStatus.PAID, maintenance2.getPaymentStatus());
        verify(maintenancesRepository, times(1)).findAllByMaster_Id(masterId);
        verify(maintenancesRepository, times(1)).saveAll(maintenances);
    }
}
