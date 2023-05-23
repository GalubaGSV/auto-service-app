package galuba.autoservice.dto.mapper;

import galuba.autoservice.dto.CarOwnerRequestDto;
import galuba.autoservice.dto.CarOwnerResponseDto;
import galuba.autoservice.model.Car;
import galuba.autoservice.model.CarOwner;
import galuba.autoservice.model.Order;
import galuba.autoservice.service.CarService;
import galuba.autoservice.service.OrderService;
import java.util.Collections;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CarOwnerMapper
        implements DtoMapper<CarOwnerRequestDto, CarOwnerResponseDto, CarOwner> {
    private final CarService carService;
    private final OrderService orderService;

    @Override
    public CarOwner mapToModel(CarOwnerRequestDto dto) {
        CarOwner carOwner = new CarOwner();
        carOwner.setName(dto.getName());
        if (dto.getCarId() != null) {
            carOwner.setCars(carService.findByIdIn(dto.getCarId()));
        } else {
            carOwner.setCars(Collections.emptyList());
        }
        if (dto.getOrderId() != null) {
            carOwner.setOrders(orderService.findAllById(dto.getOrderId()));
        } else {
            carOwner.setOrders(Collections.emptyList());
        }
        return carOwner;
    }

    @Override
    public CarOwnerResponseDto mapToDto(CarOwner model) {
        CarOwnerResponseDto carOwnerResponseDto = new CarOwnerResponseDto();
        carOwnerResponseDto.setId(model.getId());
        carOwnerResponseDto.setName(model.getName());
        if (model.getCars() != null) {
            carOwnerResponseDto.setCarId(model.getCars().stream()
                    .map(Car::getId)
                    .collect(Collectors.toList()));
        }
        if (model.getOrders() != null) {
            carOwnerResponseDto.setOrderId(model.getOrders().stream()
                    .map(Order::getId)
                    .collect(Collectors.toList()));
        }
        return carOwnerResponseDto;
    }
}
