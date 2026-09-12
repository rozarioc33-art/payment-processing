package rozarioc33_art.payment_processing.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import rozarioc33_art.payment_processing.dto.PaymentRequest;
import rozarioc33_art.payment_processing.dto.PaymentStatusRequest;
import rozarioc33_art.payment_processing.entity.Payment;
import rozarioc33_art.payment_processing.entity.PaymentStatus;
import rozarioc33_art.payment_processing.service.PaymentService;

import java.util.UUID;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public Payment createPayment(@Valid @RequestBody PaymentRequest request,
                                 @RequestHeader("Idempotency-key") String idempotencyKey) {

        return paymentService.createPayment(request, idempotencyKey);
    }


    @PatchMapping("/{paymentId}/status")
    public Payment changePaymentStatus(
            @PathVariable UUID paymentId,
            @RequestBody PaymentStatusRequest request) {

        return paymentService.changePaymentStatus(
                paymentId,
                request.getStatus()
        );
    }
}
