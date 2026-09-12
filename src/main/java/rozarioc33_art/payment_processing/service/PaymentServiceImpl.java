package rozarioc33_art.payment_processing.service;

import org.springframework.stereotype.Service;
import rozarioc33_art.payment_processing.dto.PaymentRequest;
import rozarioc33_art.payment_processing.entity.Order;
import rozarioc33_art.payment_processing.entity.Payment;
import rozarioc33_art.payment_processing.entity.PaymentStatus;
import rozarioc33_art.payment_processing.exception.InvalidOrderIdException;
import rozarioc33_art.payment_processing.exception.InvalidPaymentStatusTransitionException;
import rozarioc33_art.payment_processing.exception.OrderNotFoundException;
import rozarioc33_art.payment_processing.repository.OrderRepository;
import rozarioc33_art.payment_processing.repository.PaymentRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService{

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository, OrderRepository orderRepository) {
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public Payment createPayment(PaymentRequest request, String idempotencyKey) {

        Optional<Payment> existingPayment = paymentRepository.findByIdempotencyKey(idempotencyKey);

        if (existingPayment.isPresent()) {
            return existingPayment.get();
        }

        UUID orderId;

        try {
            orderId = UUID.fromString(request.getOrderId());
        } catch (IllegalArgumentException e) {
            throw new InvalidOrderIdException("Invalid order ID");
        }

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new OrderNotFoundException("Order not found")
                );

        Payment payment = new Payment();

        payment.setOrder(order);
        payment.setAmount(request.getAmount());
        payment.setCurrency(request.getCurrency());
        payment.setStatus(PaymentStatus.INITIATED);
        payment.setIdempotencyKey(idempotencyKey);

        return paymentRepository.save(payment);
    }

    @Override
    public Payment changePaymentStatus(UUID paymentId, PaymentStatus newStatus) {

        Optional<Payment> existingPayment = paymentRepository.findById(paymentId);

        if (existingPayment.isEmpty()) {
            throw new InvalidPaymentStatusTransitionException("Payment not found");
        }

        Payment payment = existingPayment.get();
        PaymentStatus currentStatus =  payment.getStatus();

        if (currentStatus == PaymentStatus.INITIATED) {

            if (newStatus == PaymentStatus.PROCESSING) {
                payment.setStatus(newStatus);
            } else {
                throw  new InvalidPaymentStatusTransitionException("Invalid payment status transition");
            }
        } else if (currentStatus == PaymentStatus.PROCESSING) {

            if (newStatus == PaymentStatus.SUCCESS || newStatus == PaymentStatus.FAILED) {
                payment.setStatus(newStatus);
            } else {
                throw  new InvalidPaymentStatusTransitionException("Invalid payment status transition");
            }
        } else {
            throw new InvalidPaymentStatusTransitionException("Invalid payment status transition");
        }

        return paymentRepository.save(payment);
    }
}
