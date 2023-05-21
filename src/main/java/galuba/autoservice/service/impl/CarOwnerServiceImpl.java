package galuba.autoservice.service.impl;

import galuba.autoservice.model.CarOwner;
import galuba.autoservice.repository.CarOwnerRepository;
import galuba.autoservice.service.CarOwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CarOwnerServiceImpl implements CarOwnerService {
    private final CarOwnerRepository carOwnerRepository;

    @Override
    public CarOwner save(CarOwner carOwner) {
        return carOwnerRepository.save(carOwner);
    }

    @Override
    public CarOwner findById(Long id) {
        return carOwnerRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Can't find CarOwner by id: " + id));
    }
}
