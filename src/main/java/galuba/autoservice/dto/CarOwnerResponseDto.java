package galuba.autoservice.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarOwnerResponseDto {
    private Long id;
    private List<Long> carId;
    private String name;
    private List<Long> orderId;
}
