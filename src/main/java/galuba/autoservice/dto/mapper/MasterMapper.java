package galuba.autoservice.dto.mapper;

import galuba.autoservice.dto.MasterRequestDto;
import galuba.autoservice.dto.MasterResponseDto;
import galuba.autoservice.model.Master;
import galuba.autoservice.model.Order;
import galuba.autoservice.service.OrderService;
import java.util.Collections;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MasterMapper implements DtoMapper<MasterRequestDto, MasterResponseDto, Master> {
    private final OrderService orderService;

    @Override
    public Master mapToModel(MasterRequestDto dto) {
        Master master = new Master();
        master.setName(dto.getName());
        if (dto.getOrdersId() != null) {
            master.setOrders(orderService.findAllById(dto.getOrdersId()));
        } else {
            master.setOrders(Collections.emptyList());
        }
        return master;
    }

    @Override
    public MasterResponseDto mapToDto(Master model) {
        MasterResponseDto masterResponseDto = new MasterResponseDto();
        masterResponseDto.setId(model.getId());
        masterResponseDto.setName(model.getName());
        masterResponseDto.setOrdersId(model.getOrders().stream()
                .map(Order::getId)
                .collect(Collectors.toList()));
        return masterResponseDto;
    }
}
