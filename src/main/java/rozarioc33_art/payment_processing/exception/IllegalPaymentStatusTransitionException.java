package rozarioc33_art.payment_processing.exception;

public class IllegalPaymentStatusTransitionException extends RuntimeException {
    public IllegalPaymentStatusTransitionException(String message) {
        super(message);
    }
}
