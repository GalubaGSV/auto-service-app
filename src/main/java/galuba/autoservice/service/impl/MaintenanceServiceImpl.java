package galuba.autoservice.service.impl;

import galuba.autoservice.model.Maintenance;
import galuba.autoservice.repository.MaintenancesRepository;
import galuba.autoservice.service.MaintenanceService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MaintenanceServiceImpl implements MaintenanceService {
    private final MaintenancesRepository maintenancesRepository;

    @Override
    public Maintenance save(Maintenance maintenances) {
        return maintenancesRepository.save(maintenances);
    }

    @Override
    public List<Maintenance> findByIdIn(List<Long> ids) {
        return maintenancesRepository.findByIdIn(ids);
    }

    @Override
    public Maintenance findById(Long id) {
        return maintenancesRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Can't find maintenance by id " + id));
    }
}
