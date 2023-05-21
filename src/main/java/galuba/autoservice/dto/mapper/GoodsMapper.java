package galuba.autoservice.dto.mapper;

import galuba.autoservice.dto.GoodsRequestDto;
import galuba.autoservice.dto.GoodsResponseDto;
import galuba.autoservice.model.Goods;
import galuba.autoservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class GoodsMapper implements DtoMapper<GoodsRequestDto, GoodsResponseDto, Goods>{
    private final OrderService orderService;

    @Override
    public Goods mapToModel(GoodsRequestDto dto) {
        Goods goods = new Goods();
        goods.setName(dto.getName());
        goods.setPrice(dto.getPrice());
        if (dto.getOrderId() != null) {
            goods.setOrder(orderService.findById(dto.getOrderId()));
        }
        return goods;
    }

    @Override
    public GoodsResponseDto mapToDto(Goods model) {
        GoodsResponseDto goodsResponseDto = new GoodsResponseDto();
        goodsResponseDto.setId(model.getId());
        goodsResponseDto.setName(model.getName());
        goodsResponseDto.setPrice(model.getPrice());
        if (model.getOrder() != null) {
            goodsResponseDto.setOrderId(model.getOrder().getId());
        }
        return goodsResponseDto;
    }
}
