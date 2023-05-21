package galuba.autoservice.repository;

import java.util.List;
import galuba.autoservice.model.Maintenance;
import galuba.autoservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenancesRepository extends JpaRepository<Maintenance, Long> {
    List<Maintenance> findByIdIn(List<Long> ids);

    List<Maintenance> findAllByMaster_Id(Long master_id);

    List<Maintenance> findAllByOrder_Id(Long order_id);
}
