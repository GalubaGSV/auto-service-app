package galuba.autoservice.service.impl;

import galuba.autoservice.model.Car;
import galuba.autoservice.repository.CarRepository;
import galuba.autoservice.service.CarService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Override
    public List<Car> findByIdIn(List<Long> ids) {
        return carRepository.findByIdIn(ids);
    }

    @Override
    public Car getById(Long id) {
        return carRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Cant find car by id " + id));
    }
}
