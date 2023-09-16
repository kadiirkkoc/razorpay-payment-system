package payment.service.razopay.integration.dto;

import lombok.Data;

@Data
public class SubscriptionDto {
    private String plan_id;
    private String subscription_id;
    private String customer_id;
    private String entity;
    private int total_count;
    private String period;
    private int interval;
    private String planName;
    private int amount;
    private String currency;
    private String description;
}
