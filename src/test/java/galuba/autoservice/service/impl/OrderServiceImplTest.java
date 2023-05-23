package galuba.autoservice.service.impl;

import galuba.autoservice.model.Car;
import galuba.autoservice.model.CarOwner;
import galuba.autoservice.model.Maintenance;
import galuba.autoservice.model.Order;
import galuba.autoservice.repository.GoodsRepository;
import galuba.autoservice.repository.MaintenancesRepository;
import galuba.autoservice.repository.OrderRepository;
import galuba.autoservice.service.OrderService;
import galuba.autoservice.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class OrderServiceImplTest {
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private MaintenancesRepository maintenancesRepository;

    @Mock
    private GoodsRepository goodsRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        orderService = new OrderServiceImpl(orderRepository, maintenancesRepository, goodsRepository);
    }

    @Test
    public void testSaveOrder() {
        Order order = new Order();
        when(orderRepository.save(order)).thenReturn(order);

        Order savedOrder = orderService.save(order);

        assertEquals(order, savedOrder);
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    public void testFindAllById() {
        List<Long> ids = new ArrayList<>();
        List<Order> orders = new ArrayList<>();
        when(orderRepository.findAllById(ids)).thenReturn(orders);

        List<Order> foundOrders = orderService.findAllById(ids);

        assertEquals(orders, foundOrders);
        verify(orderRepository, times(1)).findAllById(ids);
    }

    @Test
    public void testFindById_OrderExists() {
        Long id = 1L;
        Order order = new Order();
        when(orderRepository.findById(id)).thenReturn(java.util.Optional.of(order));

        Order foundOrder = orderService.findById(id);

        assertEquals(order, foundOrder);
        verify(orderRepository, times(1)).findById(id);
    }

    @Test
    public void testFindById_OrderDoesNotExist() {
        Long id = 1L;
        when(orderRepository.findById(id)).thenReturn(java.util.Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            orderService.findById(id);
        });

        assertEquals("Can't find order by id " + id, exception.getMessage());
        verify(orderRepository, times(1)).findById(id);
    }
}
