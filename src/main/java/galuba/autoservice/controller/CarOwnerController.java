package galuba.autoservice.controller;

import java.util.List;
import galuba.autoservice.dto.CarOwnerRequestDto;
import galuba.autoservice.dto.CarOwnerResponseDto;
import galuba.autoservice.dto.mapper.DtoMapper;
import galuba.autoservice.model.CarOwner;
import galuba.autoservice.model.Order;
import galuba.autoservice.service.CarOwnerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/car-owners")
public class CarOwnerController {
    private final CarOwnerService carOwnerService;
    private final DtoMapper<CarOwnerRequestDto, CarOwnerResponseDto, CarOwner> mapper;

    @Operation(summary = "Add CarOwner", description = "Creation of the essence of the CarOwner")
    @PostMapping
    public CarOwnerResponseDto add(@Parameter(schema = @Schema(type = "String",
                                   defaultValue = "{\"name\":\"bob\"}"))
                                   @RequestBody CarOwnerRequestDto carOwnerRequestDto) {
        CarOwner carOwner = carOwnerService.save(mapper.mapToModel(carOwnerRequestDto));
        return mapper.mapToDto(carOwner);
    }

    @Operation(summary = "Update CarOwner", description = "Select the CarOwner ID for the change fields")
    @PutMapping("/{id}")
    public void update(@Parameter(description = "Choose Id CarOwner",
                       schema = @Schema(type = "integer", defaultValue = "1"))
                       @PathVariable Long id,
                       @Parameter(schema = @Schema(type = "String",
                       defaultValue = "{\"name\":\"bob\"}"))
                       @RequestBody CarOwnerRequestDto carOwnerRequestDto) {
        CarOwner carOwner = mapper.mapToModel(carOwnerRequestDto);
        carOwner.setId(id);
        carOwnerService.save(carOwner);
    }

    @Operation(summary = "Find orders by CarOwner", description = "Input the CarOwner ID")
    @GetMapping("/{carOwnerId}/orders")
    public List<Order> getMasterOrders(@Parameter(description = "Choose Id CarOwner",
                                       schema = @Schema(type = "integer", defaultValue = "1"))
                                       @PathVariable Long carOwnerId) {
        return carOwnerService.findById(carOwnerId).getOrders();
    }
}
