package prog.microservice.orderms.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import prog.microservice.orderms.model.dto.ApiResponse;
import prog.microservice.orderms.model.dto.OrderResponse;
import prog.microservice.orderms.model.dto.PaginationResponse;
import prog.microservice.orderms.service.OrderService;

import java.util.Map;

@RestController
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/customers/{customerId}/orders")
    public ResponseEntity<ApiResponse<OrderResponse>> listByCustomer(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @PathVariable("customerId") Long customerId
    ) {
        var pageResponse = orderService.findAllByCustomerId(customerId, PageRequest.of(page, size));
        var totalOnOrders = orderService.findTotalOnOrdersByCustomerId(customerId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        Map.of("totalOnOrders", totalOnOrders),
                        pageResponse.getContent(),
                        PaginationResponse.fromPage(pageResponse)
                )
        );
    }
}
