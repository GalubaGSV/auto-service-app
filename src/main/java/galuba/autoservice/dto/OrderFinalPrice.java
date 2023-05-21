package galuba.autoservice.dto;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderFinalPrice {
    private Long id;
    private BigDecimal finalPrice;
}
