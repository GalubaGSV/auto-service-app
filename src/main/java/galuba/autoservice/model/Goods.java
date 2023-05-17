package galuba.autoservice.model;

import java.math.BigDecimal;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Goods {
    @Id
    private Long id;
    private String name;
    private BigDecimal price;
}
