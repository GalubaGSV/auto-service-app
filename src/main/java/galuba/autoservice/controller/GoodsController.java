package galuba.autoservice.controller;

import galuba.autoservice.dto.GoodsRequestDto;
import galuba.autoservice.dto.GoodsResponseDto;
import galuba.autoservice.dto.mapper.DtoMapper;
import galuba.autoservice.model.Goods;
import galuba.autoservice.service.GoodsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/goods")
public class GoodsController {
    private final GoodsService goodsService;
    private final DtoMapper<GoodsRequestDto, GoodsResponseDto, Goods> mapper;

    @Operation(summary = "Add goods", description = "Creation of the essence of the goods")
    @PostMapping
    public GoodsResponseDto add(@Parameter(schema = @Schema(type = "sіtring",
            defaultValue = "{\"name\":\"goods\", \"price\":100.50}"))
            @RequestBody GoodsRequestDto goodsRequestDto) {
        Goods goods = goodsService.save(mapper.mapToModel(goodsRequestDto));
        return mapper.mapToDto(goods);
    }

    @Operation(summary = "Update goods", description = "Input the goods ID for the change fields")
    @PutMapping("/{id}")
    public void update(@Parameter(description = "Choose Id goods",
                       schema = @Schema(type = "integer", defaultValue = "1"))
                       @PathVariable Long id,
                       @Parameter(schema = @Schema(type = "sіtring",
                       defaultValue = "{\"name\":\"goods\", \"price\":100.50}"))
                       @RequestBody GoodsRequestDto goodsRequestDto) {
        Goods goods = mapper.mapToModel(goodsRequestDto);
        goods.setId(id);
        goodsService.save(goods);
    }
}
