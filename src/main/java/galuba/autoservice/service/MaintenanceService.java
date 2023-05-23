package galuba.autoservice.service;

import galuba.autoservice.model.Maintenance;
import java.util.List;

public interface MaintenanceService {
    Maintenance save(Maintenance maintenances);

    List<Maintenance> findByIdIn(List<Long> ids);

    Maintenance findById(Long id);
}
