package galuba.autoservice.service.impl;

import galuba.autoservice.model.Maintenance;
import galuba.autoservice.model.Order;
import galuba.autoservice.repository.GoodsRepository;
import galuba.autoservice.repository.MaintenancesRepository;
import galuba.autoservice.repository.OrderRepository;
import galuba.autoservice.service.OrderService;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private static final double GOODS_SALE_MULTIPLE = 0.01;
    private static final double MAINTENANCE_SALE_MULTIPLE = 0.02;
    private static final String DIAGNOSTIC_SERVICE = "Diagnostics";
    private final OrderRepository orderRepository;
    private final MaintenancesRepository maintenancesRepository;
    private final GoodsRepository goodsRepository;

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> findAllById(List<Long> ids) {
        return orderRepository.findAllById(ids);
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Can't find order by id " + id));
    }

    @Override
    public BigDecimal calculateFinallyPrice(Long id) {
        Order order = findById(id);
        int orderCount = order.getCar().getCarOwner().getOrders().size();
        return calculateGoodsPrice(orderCount, id)
                .add(calculateMaintenancePrice(orderCount, id));
    }

    private BigDecimal calculateGoodsPrice(int orderCount, Long orderId) {
        double goodsSale = orderCount * GOODS_SALE_MULTIPLE;
        return goodsRepository.findAllByOrder_Id(orderId).stream()
                .map(m -> m.getPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .multiply(BigDecimal.valueOf(1 - goodsSale));
    }

    private BigDecimal calculateMaintenancePrice(int orderCount, Long orderId) {
        double maintenanceSale = orderCount * MAINTENANCE_SALE_MULTIPLE;
        List<Maintenance> allByOrderId = maintenancesRepository.findAllByOrder_Id(orderId);
        if (allByOrderId.size() > 1) {
            BigDecimal maintenancePrice = allByOrderId.stream()
                    .filter(m -> !m.getName().equals(DIAGNOSTIC_SERVICE))
                    .map(m -> m.getPrice())
                    .reduce(BigDecimal.ZERO, BigDecimal::add)
                    .multiply(BigDecimal.valueOf(1 - maintenanceSale));
            return maintenancePrice;
        }
        BigDecimal maintenancePrice = allByOrderId.stream()
                .map(m -> m.getPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .multiply(BigDecimal.valueOf(1 - maintenanceSale));
        return maintenancePrice;
    }
}
