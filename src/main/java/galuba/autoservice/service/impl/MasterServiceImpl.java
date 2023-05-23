package galuba.autoservice.service.impl;

import galuba.autoservice.model.Maintenance;
import galuba.autoservice.model.Master;
import galuba.autoservice.model.PaymentStatus;
import galuba.autoservice.repository.MaintenancesRepository;
import galuba.autoservice.repository.MasterRepository;
import galuba.autoservice.service.MasterService;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MasterServiceImpl implements MasterService {
    private static final BigDecimal SALARY_INTEREST = new BigDecimal("0.40");
    private final MasterRepository masterRepository;
    private final MaintenancesRepository maintenancesRepository;

    @Override
    public Master save(Master master) {
        return masterRepository.save(master);
    }

    @Override
    public Master findById(Long id) {
        return masterRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Can't find master by id " + id));
    }

    @Override
    public BigDecimal calculateSalary(Long id) {
        List<Maintenance> noPaidMaintenances = maintenancesRepository.findAllByMaster_Id(id)
                .stream()
                .filter(m -> m.getPaymentStatus().equals(PaymentStatus.NOT_PAID))
                .collect(Collectors.toList());

        BigDecimal salary = noPaidMaintenances.stream()
                .map(m -> m.getPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        for (Maintenance maintenance: noPaidMaintenances) {
            maintenance.setPaymentStatus(PaymentStatus.PAID);
        }
        maintenancesRepository.saveAll(noPaidMaintenances);
        return salary.multiply(SALARY_INTEREST);
    }
}
