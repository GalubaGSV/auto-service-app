package galuba.autoservice.service;

import java.util.List;
import galuba.autoservice.model.Car;

public interface CarService {
    Car save(Car car);

    List<Car> findByIdIn(List<Long> ids);

    Car getById(Long id);
}
