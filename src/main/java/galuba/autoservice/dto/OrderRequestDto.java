package galuba.autoservice.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequestDto {
    private Long carId;
    private String problemDescription;
    private LocalDate startDate;
    private List<Long> maintenancesIds;
    private List<Long> goodsIds;
    private String orderStatus;
    private BigDecimal finalPrice;
    private LocalDate finalDate;
}
