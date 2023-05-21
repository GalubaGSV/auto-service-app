package galuba.autoservice.service;

import java.math.BigDecimal;
import galuba.autoservice.model.Master;

public interface MasterService {
    Master save(Master master);

    Master findById(Long id);
    BigDecimal CalculateSalary(Long id);
}
