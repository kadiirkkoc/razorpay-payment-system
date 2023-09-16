package payment.service.razopay.integration.dto;

import lombok.Data;
import payment.service.razopay.integration.data.Note;

import java.util.List;

@Data
public class OrderDto {
    private String orderId;
    private double amount;
    private String currency;
    private String receipt;
    private List<Note> notes;
    private String paymentMethodId;

}
