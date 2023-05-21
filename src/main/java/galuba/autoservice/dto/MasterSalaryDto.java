package galuba.autoservice.dto;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MasterSalaryDto {
    private Long id;
    private String name;
    private BigDecimal salary;
}
