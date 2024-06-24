package prog.microservice.orderms.model.dto;

import prog.microservice.orderms.entity.Order;

import java.math.BigDecimal;

public record OrderResponse(
        Long id,
        Long customerId,
        BigDecimal total
) {
    public static OrderResponse fromEntity(Order order) {
        return new OrderResponse(order.getId(), order.getCustomerId(), order.getTotal());
    }
}
