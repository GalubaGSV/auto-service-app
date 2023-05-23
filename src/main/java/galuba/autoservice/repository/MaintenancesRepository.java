package galuba.autoservice.repository;

import galuba.autoservice.model.Maintenance;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenancesRepository extends JpaRepository<Maintenance, Long> {
    List<Maintenance> findByIdIn(List<Long> ids);

    List<Maintenance> findAllByMaster_Id(Long masterId);

    List<Maintenance> findAllByOrder_Id(Long orderId);
}
