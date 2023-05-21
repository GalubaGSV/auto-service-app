package galuba.autoservice.dto;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MaintenanceResponseDto {
    private Long id;
    private String name;
    private Long masterId;
    private Long orderId;
    private BigDecimal price;
    private String paymentStatus;
}
