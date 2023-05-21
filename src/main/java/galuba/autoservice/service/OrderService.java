package galuba.autoservice.service;

import java.math.BigDecimal;
import java.util.List;
import galuba.autoservice.model.Car;
import galuba.autoservice.model.Order;

public interface OrderService {
    Order save(Order order);

    List<Order> findAllById(List<Long> ids);

    Order findById(Long id);

    BigDecimal calculateFinallyPrice(Long id);
}
