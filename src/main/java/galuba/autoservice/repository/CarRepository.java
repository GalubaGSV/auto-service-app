package galuba.autoservice.repository;

import java.util.List;
import galuba.autoservice.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByIdIn(List<Long> ids);
}
