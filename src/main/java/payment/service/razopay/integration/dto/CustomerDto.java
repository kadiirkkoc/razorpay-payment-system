package payment.service.razopay.integration.dto;

import lombok.Data;

import java.util.List;

@Data
public class CustomerDto {
    private String customerId;
    private String name;
    private String contact;
    private String email;
    private String fail_existing;
    private String gstin;
}
