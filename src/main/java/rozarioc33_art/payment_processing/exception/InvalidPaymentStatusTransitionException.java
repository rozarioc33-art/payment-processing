package rozarioc33_art.payment_processing.exception;

public class InvalidPaymentStatusTransitionException extends RuntimeException {
    public InvalidPaymentStatusTransitionException(String message) {
        super(message);
    }
}
