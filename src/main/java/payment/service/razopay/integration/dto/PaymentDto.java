package payment.service.razopay.integration.dto;

import lombok.Data;

@Data
public class PaymentDto {
    private String order_id;
    private String customer_id;
    private String payment_id;
    private String payment_method;
}
