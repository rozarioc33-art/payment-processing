package rozarioc33_art.payment_processing.service;

import org.springframework.stereotype.Service;
import rozarioc33_art.payment_processing.entity.Order;
import rozarioc33_art.payment_processing.entity.OrderStatus;
import rozarioc33_art.payment_processing.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {

        this.orderRepository = orderRepository;
    }

    @Override
    public Order createOrder(Order order) {

        order.setStatus(OrderStatus.CREATED);

        return orderRepository.save(order);
    }
}
