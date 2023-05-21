package galuba.autoservice.dto.mapper;

import java.util.stream.Collectors;
import galuba.autoservice.dto.OrderRequestDto;
import galuba.autoservice.dto.OrderResponseDto;
import galuba.autoservice.model.Goods;
import galuba.autoservice.model.Maintenance;
import galuba.autoservice.model.Order;
import galuba.autoservice.model.OrderStatus;
import galuba.autoservice.service.CarService;
import galuba.autoservice.service.GoodsService;
import galuba.autoservice.service.MaintenanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderMapper  implements DtoMapper<OrderRequestDto, OrderResponseDto, Order> {
    private final CarService carService;
    private final GoodsService goodsService;
    private final MaintenanceService maintenanceService;

    @Override
    public Order mapToModel(OrderRequestDto dto) {
        Order order = new Order();
        order.setOrderStatus(OrderStatus.valueOf(dto.getOrderStatus().toUpperCase()));
        order.setCar(carService.getById(dto.getCarId()));
        order.setProblemDescription(dto.getProblemDescription());
        order.setFinalDate(dto.getFinalDate());
        order.setStartDate(dto.getStartDate());
        order.setFinalPrice(dto.getFinalPrice());
        if (dto.getGoodsIds() != null) {
            goodsService.findByIdIn(dto.getGoodsIds());
        }
        if (dto.getMaintenancesIds() != null) {
            maintenanceService.findByIdIn(dto.getMaintenancesIds());
        }
        return order;
    }

    @Override
    public OrderResponseDto mapToDto(Order model) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setId(model.getId());
        orderResponseDto.setOrderStatus(model.getOrderStatus().getValue());
        orderResponseDto.setCarId(model.getCar().getId());
        orderResponseDto.setProblemDescription(model.getProblemDescription());
        orderResponseDto.setFinalDate(model.getFinalDate());
        orderResponseDto.setStartDate(model.getStartDate());
        orderResponseDto.setFinalPrice(model.getFinalPrice());
        if (model.getGoods() != null) {
            orderResponseDto.setGoodsIds(model.getGoods().stream()
                    .map(Goods::getId)
                    .collect(Collectors.toList()));
        }
        if (model.getMaintenances() != null) {
            orderResponseDto.setMaintenancesIds(model.getMaintenances().stream()
                    .map(Maintenance::getId)
                    .collect(Collectors.toList()));
        }
        return orderResponseDto;
    }
}
