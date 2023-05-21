package galuba.autoservice.service;

import galuba.autoservice.model.CarOwner;

public interface CarOwnerService {
    CarOwner save(CarOwner carOwner);

    CarOwner findById(Long id);
}
