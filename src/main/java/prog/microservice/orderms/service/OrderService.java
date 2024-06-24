package prog.microservice.orderms.service;

import org.springframework.stereotype.Service;
import prog.microservice.orderms.entity.Order;
import prog.microservice.orderms.entity.OrderItem;
import prog.microservice.orderms.listener.dto.OrderCreatedEvent;
import prog.microservice.orderms.repository.OrderRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void save(OrderCreatedEvent event) {
        var entity = new Order();

        entity.setId(event.codigoPedido());
        entity.setCustomerId(event.codigoCliente());
        entity.setItems(getOrderItems(event));
        entity.setTotal(getTotal(event));

        orderRepository.save(entity);
    }

    private static List<OrderItem> getOrderItems(OrderCreatedEvent event) {
        return event.itens().stream().map(
                item -> new OrderItem(
                        item.produto(),
                        item.quantidade(),
                        item.preco(
                        )
                )).toList();
    }

    private BigDecimal getTotal(OrderCreatedEvent event) {
        return event.itens().stream().map(
                item -> item.preco().multiply(BigDecimal.valueOf(item.quantidade()))
        ).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
