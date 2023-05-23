package galuba.autoservice.service;

import galuba.autoservice.model.Master;
import java.math.BigDecimal;

public interface MasterService {
    Master save(Master master);

    Master findById(Long id);

    BigDecimal calculateSalary(Long id);
}
