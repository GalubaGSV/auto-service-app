package galuba.autoservice.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarOwnerRequestDto {
    private List<Long> carId;
    private String name;
    private List<Long> orderId;
}
