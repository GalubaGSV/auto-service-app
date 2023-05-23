package galuba.autoservice.repository;

import galuba.autoservice.model.Car;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByIdIn(List<Long> ids);
}
