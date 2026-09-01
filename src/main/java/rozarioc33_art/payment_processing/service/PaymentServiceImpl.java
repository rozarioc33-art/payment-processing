package rozarioc33_art.payment_processing.service;

import org.springframework.stereotype.Service;
import rozarioc33_art.payment_processing.entity.Payment;
import rozarioc33_art.payment_processing.entity.PaymentStatus;
import rozarioc33_art.payment_processing.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService{

    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment createPayment(Payment payment) {
        payment.setStatus(PaymentStatus.INITIATED);

        return paymentRepository.save(payment);
    }
}
