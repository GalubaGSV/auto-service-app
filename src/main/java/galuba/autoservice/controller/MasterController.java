package galuba.autoservice.controller;

import galuba.autoservice.dto.MasterRequestDto;
import galuba.autoservice.dto.MasterResponseDto;
import galuba.autoservice.dto.MasterSalaryDto;
import galuba.autoservice.dto.mapper.DtoMapper;
import galuba.autoservice.model.Master;
import galuba.autoservice.model.Order;
import galuba.autoservice.service.MasterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
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
@RequestMapping("/masters")
public class MasterController {
    private final MasterService masterService;
    private final DtoMapper<MasterRequestDto, MasterResponseDto, Master> mapper;

    @Operation(summary = "Add master",
            description = "Creation of the essence of the master")
    @PostMapping
    public MasterResponseDto add(@Parameter(schema = @Schema(type = "sіtring",
                                 defaultValue = "{\"name\":\"Bob Bobson\"}"))
                                 @RequestBody MasterRequestDto masterRequestDto) {
        Master master = masterService.save(mapper.mapToModel(masterRequestDto));
        return mapper.mapToDto(master);
    }

    @Operation(summary = "Update master",
            description = "Input the master ID for the change fields")
    @PutMapping("/{id}")
    public void update(@Parameter(description = "Choose Id master",
                               schema = @Schema(type = "integer", defaultValue = "1"))
                       @PathVariable Long id,
                       @Parameter(schema = @Schema(type = "sіtring",
                               defaultValue = "{\"name\":\"Bob Bobson\"}"))
                       @RequestBody MasterRequestDto masterRequestDto) {
        Master master = mapper.mapToModel(masterRequestDto);
        master.setId(id);
        masterService.save(master);
    }

    @Operation(summary = "Get master order", description = "Input master orders")
    @GetMapping("/{masterId}/orders")
    public List<Order> getMasterOrders(@Parameter(description = "Choose Id master",
                                       schema = @Schema(type = "integer", defaultValue = "1"))
                                       @PathVariable Long masterId) {
        return masterService.findById(masterId).getOrders();
    }

    @Operation(summary = "Calculate masters salary",
            description = "Calculate the master's salary. After calculation, "
                    + "all services are transferred to the paid status.")
    @GetMapping("/{masterId}/salary")
    public MasterSalaryDto getMasterSalary(@Parameter(description = "Choose Id master",
                                           schema = @Schema(type = "integer", defaultValue = "1"))
                                           @PathVariable Long masterId) {
        MasterSalaryDto masterSalaryDto = new MasterSalaryDto();
        masterSalaryDto.setId(masterId);
        masterSalaryDto.setName(masterService.findById(masterId).getName());
        masterSalaryDto.setSalary(masterService.calculateSalary(masterId));
        return masterSalaryDto;
    }
}
