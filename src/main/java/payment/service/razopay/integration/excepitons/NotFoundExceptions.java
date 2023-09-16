package payment.service.razopay.integration.excepitons;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NotFoundExceptions extends RuntimeException{
    private String message;
}
