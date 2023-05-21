package galuba.autoservice.dto.mapper;

import galuba.autoservice.dto.MaintenanceRequestDto;
import galuba.autoservice.dto.MaintenanceResponseDto;
import galuba.autoservice.model.Maintenance;
import galuba.autoservice.model.PaymentStatus;
import galuba.autoservice.service.MasterService;
import galuba.autoservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MaintenanceMapper
        implements DtoMapper<MaintenanceRequestDto, MaintenanceResponseDto, Maintenance> {
    private final OrderService orderService;
    private final MasterService masterService;

    @Override
    public Maintenance mapToModel(MaintenanceRequestDto dto) {
        Maintenance maintenance = new Maintenance();
        maintenance.setName(dto.getName());
        maintenance.setPrice(dto.getPrice());
        maintenance.setMaster(masterService.findById(dto.getMasterId()));
        if (dto.getOrderId() != null) {
            maintenance.setOrder(orderService.findById(dto.getOrderId()));
        }
        maintenance.setPaymentStatus(PaymentStatus.NOT_PAID);
        return maintenance;
    }

    @Override
    public MaintenanceResponseDto mapToDto(Maintenance model) {
        MaintenanceResponseDto maintenanceResponseDto = new MaintenanceResponseDto();
        maintenanceResponseDto.setId(model.getId());
        maintenanceResponseDto.setName(model.getName());
        maintenanceResponseDto.setMasterId(model.getMaster().getId());
        maintenanceResponseDto.setPrice(model.getPrice());
        if (model.getOrder() != null) {
            maintenanceResponseDto.setOrderId(model.getOrder().getId());
        }
        maintenanceResponseDto.setPaymentStatus(model.getPaymentStatus().getValue());
        return maintenanceResponseDto;
    }
}
