package galuba.autoservice.controller;

import galuba.autoservice.dto.CarRequestDto;
import galuba.autoservice.dto.CarResponseDto;
import galuba.autoservice.dto.mapper.DtoMapper;
import galuba.autoservice.model.Car;
import galuba.autoservice.service.CarService;
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
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;
    private final DtoMapper<CarRequestDto, CarResponseDto, Car> mapper;

    @Operation(summary = "Add car", description = "Creation of the essence of the car")
    @PostMapping
    public CarResponseDto add(@Parameter(schema = @Schema(type = "String",
                                  defaultValue = "{\n"
                                          + "  \"manufacturer\": \"BMW\",\n"
                                          + "  \"model\": \"X5\",\n"
                                          + "  \"year\": 2021,\n"
                                          + "  \"carNumber\": \"AB1234CD\",  \n"
                                          + "  \"carOwnerId\":1\n"
                                          + "}"))
                                  @RequestBody CarRequestDto carRequestDto) {
        Car car = carService.save(mapper.mapToModel(carRequestDto));
        return mapper.mapToDto(car);
    }

    @Operation(summary = "Update car", description = "Input the car ID for the change fields")
    @PutMapping("/{id}")
    public void update(@Parameter(description = "Choose Id car",
                       schema = @Schema(type = "integer", defaultValue = "1"))
                       @PathVariable Long id,
                       @Parameter(schema = @Schema(type = "String",
                               defaultValue = "{\n"
                                       + "  \"manufacturer\": \"BMW\",\n"
                                       + "  \"model\": \"X5\",\n"
                                       + "  \"year\": 2021,\n"
                                       + "  \"carNumber\": \"AB1234CD\",  \n"
                                       + "  \"carOwnerId\":1\n"
                                       + "}"))
                       @RequestBody CarRequestDto carRequestDto) {
        Car car = mapper.mapToModel(carRequestDto);
        car.setId(id);
        carService.save(car);
    }
}
