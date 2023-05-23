package galuba.autoservice.service;

import galuba.autoservice.model.Order;
import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    Order save(Order order);

    List<Order> findAllById(List<Long> ids);

    Order findById(Long id);

    BigDecimal calculateFinallyPrice(Long id);
}
