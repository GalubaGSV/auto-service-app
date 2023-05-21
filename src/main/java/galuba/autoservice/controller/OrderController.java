package galuba.autoservice.controller;

import java.time.LocalDate;
import galuba.autoservice.dto.OrderFinalPrice;
import galuba.autoservice.dto.OrderRequestDto;
import galuba.autoservice.dto.OrderResponseDto;
import galuba.autoservice.dto.mapper.DtoMapper;
import galuba.autoservice.model.Goods;
import galuba.autoservice.model.Order;
import galuba.autoservice.model.OrderStatus;
import galuba.autoservice.service.GoodsService;
import galuba.autoservice.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final DtoMapper<OrderRequestDto, OrderResponseDto, Order> mapper;
    private final GoodsService goodsService;

    @Operation(summary = "Add new order", description = "Add order in request body.")
    @PostMapping
    public OrderResponseDto add(@Parameter(schema = @Schema(type = "String", defaultValue = "{\"carId\": 1, "
                                        + "\"problemDescription\": \"Somting wrong\", "
                                        + "\"startDate\": \"2023-05-18T10:30:00Z\", "
                                        + "\"orderStatus\": \"Accepted\" }"))
                                @RequestBody OrderRequestDto orderRequestDto) {
        Order order = orderService.save(mapper.mapToModel(orderRequestDto));
        return mapper.mapToDto(order);
    }

    @Operation(summary = "Add goods to order", description = "Add goods to order. "
            + "Input the order as the first parameter and the product as the second parameter")
    @PostMapping("/{orderId}/goods/{goodsIs}")
    public OrderResponseDto addGoodsToOrder(@Parameter(description = "Choose Id order",
                                schema = @Schema(type = "integer", defaultValue = "1"))
                                @PathVariable Long orderId,
                                @Parameter(description = "Choose Id goods",
                                schema = @Schema(type = "integer", defaultValue = "1"))
                                @PathVariable Long goodsIs) {
        Goods goods = goodsService.findById(goodsIs);
        Order order = orderService.findById(orderId);
        goods.setOrder(order);
        goodsService.save(goods);
        return mapper.mapToDto(order);
    }

    @Operation(summary = "Update order", description = "Input order and add body.")
    @PutMapping("/{id}")
    public void update(@Parameter(description = "Choose Id order",
                       schema = @Schema(type = "integer", defaultValue = "1"))
                       @PathVariable Long id,
                       @Parameter(schema = @Schema(type = "String", defaultValue = "{\"carId\": 1, "
                               + "\"problemDescription\": \"Somting wrong\", "
                               + "\"startDate\": \"2023-05-18T10:30:00Z\", "
                               + "\"orderStatus\": \"Accepted\" }"))
                       @RequestBody OrderRequestDto orderRequestDto) {
        Order order = mapper.mapToModel(orderRequestDto);
        order.setId(id);
        orderService.save(order);
    }

    @Operation(summary = "Update status for order",
            description = "Input order and changes status. Status can be: "
                    + "Accepted, In_progress, Successfully_completed, Not_successfully_completed, Paid")
    @PutMapping("/{id}/{newStatus}")
    public OrderResponseDto updateStatus(@Parameter(description = "Choose Id order",
                                   schema = @Schema(type = "integer", defaultValue = "1"))
                                   @PathVariable Long id,
                                   @Parameter(description = "Choose Id order",
                                   schema = @Schema(defaultValue = "Accepted"))
                                   @PathVariable String newStatus) {
        Order order = orderService.findById(id);
        order.setOrderStatus(OrderStatus.valueOf(newStatus.toUpperCase()));
        if (order.getOrderStatus().equals(OrderStatus.SUCCESSFULLY_COMPLETED)
                || order.getOrderStatus().equals(OrderStatus.NOT_SUCCESSFULLY_COMPLETED)) {
            order.setFinalDate(LocalDate.now());
        }
        orderService.save(order);
        return mapper.mapToDto(order);
    }

    @Operation(summary = "Calculate final price for order",
            description = "Calculation of the total amount of the order, taking into account discounts.")
    @GetMapping("/{orderId}/calculatePrice")
    public OrderFinalPrice getMasterSalary(@Parameter(description = "Choose Id order",
                                           schema = @Schema(type = "integer", defaultValue = "1"))
                                           @PathVariable Long orderId) {
        OrderFinalPrice orderFinalPrice = new OrderFinalPrice();
        orderFinalPrice.setId(orderId);
        orderFinalPrice.setFinalPrice(orderService.calculateFinallyPrice(orderId));
        return orderFinalPrice;
    }
}
