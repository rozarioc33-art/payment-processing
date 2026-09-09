package rozarioc33_art.payment_processing.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rozarioc33_art.payment_processing.dto.OrderRequest;
import rozarioc33_art.payment_processing.entity.Order;
import rozarioc33_art.payment_processing.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Order createOrder(@Valid @RequestBody OrderRequest request) {

        Order order = new Order();

        order.setAmount(request.getAmount());
        order.setCurrency(request.getCurrency());

        return orderService.createOrder(order);
    }
}
