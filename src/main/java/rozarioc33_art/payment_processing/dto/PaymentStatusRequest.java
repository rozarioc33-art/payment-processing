package rozarioc33_art.payment_processing.dto;

import rozarioc33_art.payment_processing.entity.PaymentStatus;

public class PaymentStatusRequest {

    private PaymentStatus status;

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }
}
