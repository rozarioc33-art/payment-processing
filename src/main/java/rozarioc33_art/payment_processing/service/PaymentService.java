package rozarioc33_art.payment_processing.service;

import rozarioc33_art.payment_processing.dto.PaymentRequest;
import rozarioc33_art.payment_processing.entity.Payment;

public interface PaymentService {
    Payment createPayment(PaymentRequest request, String idempotencyKey);
}
