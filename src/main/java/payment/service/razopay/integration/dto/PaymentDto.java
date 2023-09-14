package payment.service.razopay.integration.dto;

import lombok.Data;

@Data
public class PaymentDto {
    private String order_id;
    private String customer_id;
    private String payment_id;
    private String payment_method;
    private String card_number;
    private int exp_month;
    private int exp_year;
    private String cvc;
    private String card_holder_name;
}
