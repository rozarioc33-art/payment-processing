package rozarioc33_art.payment_processing.service;

import org.springframework.stereotype.Service;
import rozarioc33_art.payment_processing.dto.PaymentRequest;
import rozarioc33_art.payment_processing.entity.Payment;
import rozarioc33_art.payment_processing.entity.PaymentStatus;
import rozarioc33_art.payment_processing.repository.PaymentRepository;

import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService{

    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment createPayment(PaymentRequest request, String idempotencyKey) {

        Optional<Payment> existingPayment = paymentRepository.findByIdempotencyKey(idempotencyKey);

        if (existingPayment.isPresent()) {
            return existingPayment.get();
        }

        Payment payment = new Payment();

        payment.setOrderId(request.getOrderId());
        payment.setAmount(request.getAmount());
        payment.setCurrency(request.getCurrency());
        payment.setStatus(PaymentStatus.INITIATED);
        payment.setIdempotencyKey(idempotencyKey);

        return paymentRepository.save(payment);
    }
}
