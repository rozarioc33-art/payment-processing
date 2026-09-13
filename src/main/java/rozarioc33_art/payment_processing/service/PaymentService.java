package rozarioc33_art.payment_processing.service;

import rozarioc33_art.payment_processing.dto.PaymentRequest;
import rozarioc33_art.payment_processing.entity.Payment;
import rozarioc33_art.payment_processing.entity.PaymentStatus;

import java.util.UUID;

public interface PaymentService {

    Payment createPayment(PaymentRequest request, String idempotencyKey);

    Payment changePaymentStatus(UUID paymentId, PaymentStatus newStatus);

    Payment getPaymentById(UUID paymentId);
}
