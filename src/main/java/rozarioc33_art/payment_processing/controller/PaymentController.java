package rozarioc33_art.payment_processing.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import rozarioc33_art.payment_processing.dto.PaymentRequest;
import rozarioc33_art.payment_processing.entity.Payment;
import rozarioc33_art.payment_processing.service.PaymentService;

@RestController
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/api/payments")
    public Payment createPayment(@Valid @RequestBody PaymentRequest request,
                                 @RequestHeader("Idempotency-key") String idempotencyKey) {

        return paymentService.createPayment(request, idempotencyKey);
    }
}
