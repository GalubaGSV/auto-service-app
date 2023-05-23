package galuba.autoservice.controller;

import galuba.autoservice.dto.MaintenanceRequestDto;
import galuba.autoservice.dto.MaintenanceResponseDto;
import galuba.autoservice.dto.mapper.DtoMapper;
import galuba.autoservice.model.Maintenance;
import galuba.autoservice.model.PaymentStatus;
import galuba.autoservice.service.MaintenanceService;
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
@RequestMapping("/maintenances")
public class MaintenanceController {
    private final MaintenanceService maintenanceService;
    private final DtoMapper<MaintenanceRequestDto, MaintenanceResponseDto, Maintenance> mapper;

    @Operation(summary = "Add maintenance",
            description = "Creation of the essence of the maintenance")
    @PostMapping
    public MaintenanceResponseDto add(@Parameter(schema = @Schema(type = "sіtring",
                                    defaultValue = "{\"name\":\"Some service\", "
                                            + "\"masterId\":1, "
                                            + "\"orderId\":1, "
                                            + "\"price\":500}"))
                                    @RequestBody MaintenanceRequestDto maintenanceRequestDto) {
        Maintenance maintenance = maintenanceService.save(mapper.mapToModel(maintenanceRequestDto));
        return mapper.mapToDto(maintenance);
    }

    @Operation(summary = "Update maintenance",
            description = "Input the maintenance ID for the change fields")
    @PutMapping("/{id}")
    public void update(@Parameter(description = "Choose Id maintenance",
                       schema = @Schema(type = "integer", defaultValue = "1"))
                       @PathVariable Long id,
                       @Parameter(schema = @Schema(type = "sіtring",
                               defaultValue = "{\"name\":\"Some service\", "
                                       + "\"masterId\":1, "
                                       + "\"orderId\":1, "
                                       + "\"price\":500}"))
                       @RequestBody MaintenanceRequestDto maintenanceRequestDto) {
        Maintenance maintenance = mapper.mapToModel(maintenanceRequestDto);
        maintenance.setId(id);
        maintenanceService.save(maintenance);
    }

    @Operation(summary = "Update payment status for maintenance",
            description = "Choose maintenance and changes status. Status can be: "
                    + "Paid, Not_paid")
    @PutMapping("/{id}/{newStatus}")
    public MaintenanceResponseDto update(@Parameter(description = "Choose Id maintenance",
                       schema = @Schema(type = "integer", defaultValue = "1"))
                       @PathVariable Long id,
                       @Parameter(schema = @Schema(defaultValue = "Not_paid"))
                       @PathVariable String newStatus) {
        Maintenance maintenance = maintenanceService.findById(id);
        maintenance.setPaymentStatus(PaymentStatus.valueOf(newStatus.toUpperCase()));
        maintenanceService.save(maintenance);
        return mapper.mapToDto(maintenance);
    }
}
