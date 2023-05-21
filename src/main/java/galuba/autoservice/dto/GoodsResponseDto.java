package galuba.autoservice.dto;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoodsResponseDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private Long orderId;
}
