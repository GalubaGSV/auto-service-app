package galuba.autoservice.service;

import java.util.List;
import galuba.autoservice.model.Goods;
import galuba.autoservice.model.Maintenance;

public interface MaintenanceService {
    Maintenance save(Maintenance maintenances);

    List<Maintenance> findByIdIn(List<Long> ids);

    Maintenance findById(Long id);
}
