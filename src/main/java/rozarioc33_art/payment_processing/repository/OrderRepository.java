package rozarioc33_art.payment_processing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rozarioc33_art.payment_processing.entity.Order;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {

}
