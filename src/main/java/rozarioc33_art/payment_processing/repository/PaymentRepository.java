package rozarioc33_art.payment_processing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rozarioc33_art.payment_processing.entity.Payment;

import java.util.Optional;
import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {

    Optional<Payment> findByIdempotencyKey(String idempotencyKey);
}
