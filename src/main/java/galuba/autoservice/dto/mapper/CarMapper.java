package galuba.autoservice.dto.mapper;

import galuba.autoservice.dto.CarRequestDto;
import galuba.autoservice.dto.CarResponseDto;
import galuba.autoservice.model.Car;
import galuba.autoservice.service.CarOwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CarMapper implements DtoMapper<CarRequestDto, CarResponseDto, Car> {
    private final CarOwnerService carOwnerService;

    @Override
    public Car mapToModel(CarRequestDto dto) {
        Car car = new Car();
        car.setCarNumber(dto.getCarNumber());
        car.setModel(dto.getModel());
        car.setYear(dto.getYear());
        car.setManufacturer(dto.getManufacturer());
        car.setCarOwner(carOwnerService.findById(dto.getCarOwnerId()));
        return car;
    }

    @Override
    public CarResponseDto mapToDto(Car model) {
        CarResponseDto carResponseDto = new CarResponseDto();
        carResponseDto.setId(model.getId());
        carResponseDto.setCarNumber(model.getCarNumber());
        carResponseDto.setYear(model.getYear());
        carResponseDto.setManufacturer(model.getManufacturer());
        carResponseDto.setModel(model.getModel());
        carResponseDto.setCarOwnerId(model.getId());
        return carResponseDto;
    }
}
