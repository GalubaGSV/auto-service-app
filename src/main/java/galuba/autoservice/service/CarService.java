package galuba.autoservice.service;

import galuba.autoservice.model.Car;
import java.util.List;

public interface CarService {
    Car save(Car car);

    List<Car> findByIdIn(List<Long> ids);

    Car getById(Long id);
}
