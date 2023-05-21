package galuba.autoservice.repository;

import java.util.List;
import galuba.autoservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository  extends JpaRepository<Order, Long> {
    List<Order> findByIdIn(List<Long> ids);
}
