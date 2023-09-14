package payment.service.razopay.integration.dto;

import lombok.Data;

@Data
public class TokenDto {
    private String number;
    private String expiry_month;
    private String expiry_year;
    private String cvv;
    private String cardholder;
    private String customer_id;
    private String token_id;
}
